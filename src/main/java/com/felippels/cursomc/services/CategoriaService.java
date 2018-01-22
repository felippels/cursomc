package com.felippels.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Categoria;
import com.felippels.cursomc.domain.Cliente;
import com.felippels.cursomc.dto.CategoriaDTO;
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
		Categoria categoriaNovo = find(categoria.getId());
		updateData(categoriaNovo , categoria);
		return repositoryCategoria.save (categoriaNovo);	 
		
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repositoryCategoria.delete (id);	
		} catch (DataIntegrityViolationException e) {
			throw new com.felippels.cursomc.services.exceptions.DataIntegrityViolationException("não é possível excluir uma categoria que possue produtos, ID: " + id.toString() +", Tipo:" + Categoria.class.getName());
		}
			 
		
	}
	public List<Categoria> findAll() {
		List<Categoria> categoriaLista = repositoryCategoria.findAll();	 
		if (categoriaLista==null) {
			throw new ObjectNotFoundException("Busca por categoria não retornou resulado! Tipo:" + Categoria.class.getName()); 
		}
		return categoriaLista;	
		}
	
	public Page<Categoria> findPage(Integer page , Integer linesPerpage ,String orderBy, String direction ){
		PageRequest pageRequest = new PageRequest(page ,linesPerpage,Direction.valueOf(direction), orderBy );
		return  repositoryCategoria.findAll(pageRequest);
	}
	
	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome()); 
	}
	private void updateData(Categoria categoriaNovo ,Categoria categoria) {
		categoriaNovo.setNome(categoria.getNome());	 
		
	}
	
}
