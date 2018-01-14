package com.felippels.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Categoria;
import com.felippels.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repositoryCategoria;
	
	public Categoria buscar(Integer id) {
	Categoria categoria = repositoryCategoria.findOne(id);	 
		
		return categoria;
	}
	
}
