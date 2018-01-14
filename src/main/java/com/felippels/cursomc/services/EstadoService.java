package com.felippels.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Estado;
import com.felippels.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	EstadoRepository repositoryEstado;
	
	public Estado buscar(Integer id) {
	Estado estado = repositoryEstado.findOne(id);	 
		
		return estado;
	}

}
