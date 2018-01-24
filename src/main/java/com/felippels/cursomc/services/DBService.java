package com.felippels.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Categoria;
import com.felippels.cursomc.domain.Cidade;
import com.felippels.cursomc.domain.Cliente;
import com.felippels.cursomc.domain.Endereco;
import com.felippels.cursomc.domain.Estado;
import com.felippels.cursomc.domain.ItemPedido;
import com.felippels.cursomc.domain.Pagamento;
import com.felippels.cursomc.domain.PagamentoComBoleto;
import com.felippels.cursomc.domain.PagamentoComCartao;
import com.felippels.cursomc.domain.Pedido;
import com.felippels.cursomc.domain.Produto;
import com.felippels.cursomc.domain.enums.EstadoPagamento;
import com.felippels.cursomc.domain.enums.TipoPessoa;
import com.felippels.cursomc.repositories.CategoriaRepository;
import com.felippels.cursomc.repositories.CidadeRepository;
import com.felippels.cursomc.repositories.ClienteRepository;
import com.felippels.cursomc.repositories.EnderecoRepository;
import com.felippels.cursomc.repositories.EstadoRepository;
import com.felippels.cursomc.repositories.ItemPedidoRepository;
import com.felippels.cursomc.repositories.PagamentoRepository;
import com.felippels.cursomc.repositories.PedidoRepository;
import com.felippels.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	@Autowired
	private CategoriaRepository categoriaRepositorio; 
	@Autowired
	private ProdutoRepository produtoRepositorio;
	@Autowired
	private EstadoRepository estadoRepositorio; 
	@Autowired
	private CidadeRepository cidadeRepositorio;
	@Autowired
	private ClienteRepository clienteRepositorio;
	@Autowired
	private EnderecoRepository enderecoRepositorio;
	@Autowired
	private PedidoRepository pedidoRepositorio;
	@Autowired
	private PagamentoRepository pagamentoRepositorio;
	@Autowired
	private ItemPedidoRepository itemPedidoRepositorio;
	
	
	public void instantiateDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "almoxarife");
		Categoria cat4 = new Categoria(null, "Contabilidade");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Recepção");
		Categoria cat7 = new Categoria(null, "Diretoria");
		Categoria cat8 = new Categoria(null, "Estoque");

		Produto prod1 = new Produto(null, "Computador", 800.00);
		Produto prod2 = new Produto(null, "Mouse", 20.00);
		Produto prod3 = new Produto(null, "Canetar", 1.20);
		Produto prod4 = new Produto(null, "Café", 4.50);
		Produto prod5 = new Produto(null, "livro", 45.50);

		Produto prod6 = new Produto(null, "Computado2r", 800.00);
		Produto prod7 = new Produto(null, "Mouse2", 20.00);
		Produto prod8 = new Produto(null, "Canetar2", 1.20);
		Produto prod9 = new Produto(null, "Café2", 4.50);
		Produto prod10 = new Produto(null, "livro2", 45.50);

		cat1.getProdutosLista().addAll(Arrays.asList(prod1, prod2));
		cat2.getProdutosLista().addAll(Arrays.asList(prod3));
		cat3.getProdutosLista().addAll(Arrays.asList(prod4));
		cat4.getProdutosLista().addAll(Arrays.asList(prod5));

		cat5.getProdutosLista().addAll(Arrays.asList(prod6, prod7));
		cat6.getProdutosLista().addAll(Arrays.asList(prod8));
		cat7.getProdutosLista().addAll(Arrays.asList(prod9));
		cat8.getProdutosLista().addAll(Arrays.asList(prod10));

		prod1.getCategoriasLista().addAll(Arrays.asList(cat1));
		prod2.getCategoriasLista().addAll(Arrays.asList(cat1));
		prod3.getCategoriasLista().addAll(Arrays.asList(cat2));
		prod4.getCategoriasLista().addAll(Arrays.asList(cat3));
		prod5.getCategoriasLista().addAll(Arrays.asList(cat4));

		prod6.getCategoriasLista().addAll(Arrays.asList(cat5));
		prod7.getCategoriasLista().addAll(Arrays.asList(cat5));
		prod8.getCategoriasLista().addAll(Arrays.asList(cat6));
		prod9.getCategoriasLista().addAll(Arrays.asList(cat7));
		prod10.getCategoriasLista().addAll(Arrays.asList(cat8));

		categoriaRepositorio.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		produtoRepositorio.save(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10));

		Estado estado1 = new Estado(null, "Ceará");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Fortaleza", estado1);
		Cidade cidade2 = new Cidade(null, "Campinas", estado2);
		estado1.getCidadeLsta().add(cidade1);
		estado2.getCidadeLsta().add(cidade2);

		estadoRepositorio.save(Arrays.asList(estado1, estado2));
		cidadeRepositorio.save(Arrays.asList(cidade1, cidade2));

		Cliente cliente1 = new Cliente(null, "Ana", "ana@email.com", "023456789", TipoPessoa.PESSOAFISICA);
		Cliente cliente2 = new Cliente(null, "Mirian", "Mirian@email.com", "123456789", TipoPessoa.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("12345678", "87654321"));
		cliente2.getTelefones().addAll(Arrays.asList("12345678", "87654321"));

		Endereco endereco1 = new Endereco(null, "Assunção", "1461", "ap 402", "Fátima", "600000", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Acioli", "195", "altos", "PI", "600000", cliente2, cidade1);
		cliente1.getEnderecoLista().add(endereco1);
		cliente2.getEnderecoLista().add(endereco2);

		clienteRepositorio.save(Arrays.asList(cliente1, cliente2));
		enderecoRepositorio.save(Arrays.asList(endereco1, endereco2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido pedido = new Pedido(null, sdf.parse("16/01/2018 10:00"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("16/01/2018 15:40"), cliente2, endereco2);

		Pagamento pagamento = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido, 6);
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
				sdf.parse("20/01/2018 10:00"), null);

		pedido.setPagamento(pagamento);
		pedido2.setPagamento(pagamento2);

		pedidoRepositorio.save(Arrays.asList(pedido, pedido2));
		pagamentoRepositorio.save(Arrays.asList(pagamento, pagamento2));

		ItemPedido itmPedido1 = new ItemPedido(pedido, prod1, 0.00, 2, 800.00);
		ItemPedido itmPedido2 = new ItemPedido(pedido, prod3, 0.00, 8, 30.00);
		ItemPedido itmPedido3 = new ItemPedido(pedido2, prod2, 10.00, 10, 7.00);

		pedido.getItemPedidoLista().addAll(Arrays.asList(itmPedido1, itmPedido2));
		pedido2.getItemPedidoLista().addAll(Arrays.asList(itmPedido3));

		itemPedidoRepositorio.save(Arrays.asList(itmPedido1, itmPedido2, itmPedido3));
	}
}
