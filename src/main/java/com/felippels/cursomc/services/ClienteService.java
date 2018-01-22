package com.felippels.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Cidade;
import com.felippels.cursomc.domain.Cliente;
import com.felippels.cursomc.domain.Endereco;
import com.felippels.cursomc.domain.enums.TipoPessoa;
import com.felippels.cursomc.dto.ClienteDTO;
import com.felippels.cursomc.dto.ClienteNewDTO;
import com.felippels.cursomc.repositories.CidadeRepository;
import com.felippels.cursomc.repositories.ClienteRepository;
import com.felippels.cursomc.repositories.EnderecoRepository;
import com.felippels.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repositoryCliente;

	@Autowired
	CidadeRepository repositoryCidade;
	@Autowired
	EnderecoRepository repositoryEndereco;
	
	public Cliente find(Integer id) {
		Cliente cliente = repositoryCliente.findOne(id);	 
		if (cliente==null) {
			throw new ObjectNotFoundException("Busca por Cliente não retornou resulado! id: " + id.toString() +", Tipo:" + Cliente.class.getName()); 
		}
			return cliente;
		}
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		repositoryCliente.save (cliente);
		repositoryEndereco.save (cliente.getEnderecoLista());
		return cliente;	 
		
	}
	public Cliente update(Cliente cliente) {
		Cliente clienteNovo = find(cliente.getId());
		updateData(clienteNovo , cliente);
		return repositoryCliente.save (clienteNovo);	 
		
	}
	public void delete(Integer id) {
		find(id);
		try {
			repositoryCliente.delete (id);	
		} catch (DataIntegrityViolationException e) {
			throw new com.felippels.cursomc.services.exceptions.DataIntegrityViolationException("não é possível excluir uma cliente que possue pedidos, ID: " + id.toString() +", Tipo:" + Cliente.class.getName());
		}
			 
		
	}
	public List<Cliente> findAll() {
		List<Cliente> clienteLista = repositoryCliente.findAll();	 
		if (clienteLista==null) {
			throw new ObjectNotFoundException("Busca por cliente não retornou resulado! Tipo:" + Cliente.class.getName()); 
		}
		return clienteLista;	
		}
	
	public Page<Cliente> findPage(Integer page , Integer linesPerpage ,String orderBy, String direction ){
		PageRequest pageRequest = new PageRequest(page ,linesPerpage,Direction.valueOf(direction), orderBy );
		return  repositoryCliente.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO objDto) {
		
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null,null); 
	}
	public Cliente fromDto(ClienteNewDTO objDto) {
		
		 Cliente cliente  = new Cliente ( (objDto.getId()==null? null:objDto.getId()), objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoPessoa.toEnum(objDto.getTipoPessoa()));
		 Cidade cidade = repositoryCidade.findOne(objDto.getIdCidade());
		 Endereco endereco = new Endereco(null, objDto.getLogradouro(),objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente, cidade);
		 cliente.getEnderecoLista().add(endereco);
		 cliente.getTelefones().add(objDto.getTelefone1());
		 
		 if (objDto.getTelefone2()!=null) { cliente.getTelefones().add(objDto.getTelefone2());}
		 if (objDto.getTelefone3()!=null) { cliente.getTelefones().add(objDto.getTelefone3());}
		 
		return cliente ;
	}
	private void updateData(Cliente clienteNovo ,Cliente cliente) {
		clienteNovo.setNome(cliente.getNome());
		clienteNovo.setEmail(cliente.getEmail());	 
		
	}

}
