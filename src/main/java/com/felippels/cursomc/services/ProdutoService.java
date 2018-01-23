package com.felippels.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Categoria;
import com.felippels.cursomc.domain.Produto;
import com.felippels.cursomc.dto.ProdutoDTO;
import com.felippels.cursomc.repositories.CategoriaRepository;
import com.felippels.cursomc.repositories.ProdutoRepository;
import com.felippels.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repositoryProduto;

	@Autowired
	CategoriaRepository repositoryCategoria;

	public Produto find(Integer id) {
		Produto produto = repositoryProduto.findOne(id);
		if (produto == null) {
			throw new ObjectNotFoundException("Busca por produto não retornou resulado! id: " + id.toString()
					+ ", Tipo:" + Produto.class.getName());
		}
		return produto;
	}

	public Produto insert(Produto produto) {
		produto.setId(null);
		return repositoryProduto.save(produto);

	}

	public Produto update(Produto produto) {
		Produto produtoNovo = find(produto.getId());
		updateData(produtoNovo, produto);
		return repositoryProduto.save(produtoNovo);

	}

	public void delete(Integer id) {
		find(id);
		try {
			repositoryProduto.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.felippels.cursomc.services.exceptions.DataIntegrityViolationException(
					"não é possível excluir uma produto que possue produtos, ID: " + id.toString() + ", Tipo:"
							+ Produto.class.getName());
		}

	}

	public List<Produto> findAll() {
		List<Produto> produtoLista = repositoryProduto.findAll();
		if (produtoLista == null) {
			throw new ObjectNotFoundException(
					"Busca por produto não retornou resulado! Tipo:" + Produto.class.getName());
		}
		return produtoLista;
	}

	public Page<Produto> findPage(Integer page, Integer linesPerpage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerpage, Direction.valueOf(direction), orderBy);
		return repositoryProduto.findAll(pageRequest);
	}

	private void updateData(Produto produtoNovo, Produto produto) {
		produtoNovo.setNome(produto.getNome());

	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerpage, String orderBy,
			String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerpage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = repositoryCategoria.findAll(ids);
		return repositoryProduto.search(nome, categorias, pageRequest);
	}

	public Produto fromDto(ProdutoDTO objDto) {

		return new Produto(objDto.getId(), objDto.getNome(), objDto.getPreco());
	}

}
