package com.felippels.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Cliente;
import com.felippels.cursomc.dto.ClienteDTO;
import com.felippels.cursomc.repositories.ClienteRepository;
import com.felippels.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repositoryCliente;
	
	public Cliente find(Integer id) {
		Cliente cliente = repositoryCliente.findOne(id);	 
		if (cliente==null) {
			throw new ObjectNotFoundException("Busca por Cliente não retornou resulado! id: " + id.toString() +", Tipo:" + Cliente.class.getName()); 
		}
			return cliente;
		}
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return repositoryCliente.save (cliente);	 
		
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

	private void updateData(Cliente clienteNovo ,Cliente cliente) {
		clienteNovo.setNome(cliente.getNome());
		clienteNovo.setEmail(cliente.getEmail());	 
		
	}

}
