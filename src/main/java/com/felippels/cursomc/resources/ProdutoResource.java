package com.felippels.cursomc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felippels.cursomc.domain.Produto;
import com.felippels.cursomc.dto.ProdutoDTO;
import com.felippels.cursomc.resources.utils.URL;
import com.felippels.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto>  find(@PathVariable Integer id) {
		Produto produto = produtoService.find(id);
		
		return ResponseEntity.ok().body(produto);
		
	}
	

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>>  findAll(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Produto> produtoLista = produtoService.findPage(page,linesPerPage, orderBy, direction);
		Page<ProdutoDTO> produtoListaDto =produtoLista.map(obj -> new ProdutoDTO(obj));  
		return ResponseEntity.ok().body(produtoListaDto);
	}
	@RequestMapping(value="/produtos_categorias", method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>>  findAll(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		List<Integer> ids = URL.decodeIntList(categorias) ;
		String nomeDecoded = URL.decodeParam(nome); 
		Page<Produto> produtoLista = produtoService.search(nomeDecoded, ids, page,linesPerPage, orderBy, direction);
		Page<ProdutoDTO> produtoListaDto =produtoLista.map(obj -> new ProdutoDTO(obj));  
		return ResponseEntity.ok().body(produtoListaDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoDTO produtoDto) {
		Produto produto =produtoService.fromDto(produtoDto); 
		produto = produtoService.insert(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ProdutoDTO produtoDto, @PathVariable Integer id) {
		Produto produto =produtoService.fromDto(produtoDto);
		produto.setId(id);
		produto = produtoService.update(produto);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete( @PathVariable Integer id) {
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
