package com.felippels.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Categoria;
import com.felippels.cursomc.repositories.CategoriaRepository;
import com.felippels.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repositoryCategoria;
	
	public Categoria find(Integer id) {
	Categoria categoria = repositoryCategoria.findOne(id);	 
		if (categoria==null) {
			throw new ObjectNotFoundException("Busca por categoria não retornou resulado! id: " + id.toString() +", Tipo:" + Categoria.class.getName()); 
		}
		return categoria;
	}
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repositoryCategoria.save (categoria);	 
		
	}
	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repositoryCategoria.save (categoria);	 
		
	}
	public void delete(Integer id) {
		find(id);
		try {
			repositoryCategoria.delete (id);	
		} catch (DataIntegrityViolationException e) {
			throw new com.felippels.cursomc.services.exceptions.DataIntegrityViolationException("não é possível excluir uma categoria que possue produtos, ID: " + id.toString() +", Tipo:" + Categoria.class.getName());
		}
			 
		
	}
}
