package com.felippels.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Cliente;
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

}
