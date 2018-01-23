package com.felippels.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.ItemPedido;
import com.felippels.cursomc.domain.PagamentoComBoleto;
import com.felippels.cursomc.domain.Pedido;
import com.felippels.cursomc.domain.enums.EstadoPagamento;
import com.felippels.cursomc.repositories.ItemPedidoRepository;
import com.felippels.cursomc.repositories.PagamentoRepository;
import com.felippels.cursomc.repositories.PedidoRepository;
import com.felippels.cursomc.repositories.ProdutoRepository;
import com.felippels.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repositoryPedido;

	@Autowired
	PagamentoRepository repositoryPagamento;
	
	@Autowired
	BoletoService boletoService;

	@Autowired
	ProdutoRepository repositoryProduto;

	@Autowired
	ItemPedidoRepository repositoryItemPedido;
	
	public Pedido find(Integer id) {
	Pedido pedido = repositoryPedido.findOne(id);	 
	if (pedido==null) {
		throw new ObjectNotFoundException("Busca por pedido não retornou resulado! id: " + id.toString() +", Tipo:" + Pedido.class.getName()); 
	}
		return pedido;
	}
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamento, pedido.getInstante()); 
		}
		
		repositoryPedido.save(pedido);
		repositoryPagamento.save(pedido.getPagamento());
		
		for (ItemPedido item : pedido.getItemPedidoLista()) {
			item.setDesconto(0.0);
			item.setPreco(repositoryProduto.findOne(item.getProduto().getId()).getPreco());
			item.setPedido(pedido);
		}
		repositoryItemPedido.save(pedido.getItemPedidoLista());
		return pedido;

	}
}
