package com.felippels.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Estado;
import com.felippels.cursomc.repositories.EstadoRepository;
import com.felippels.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	EstadoRepository repositoryEstado;
	
	public Estado buscar(Integer id) {
	Estado estado = repositoryEstado.findOne(id);	 
	if (estado==null) {
		throw new ObjectNotFoundException("Busca por estado não retornou resulado! id: " + id.toString() +", Tipo:" + Estado.class.getName()); 
	}
		return estado;
	}

}
