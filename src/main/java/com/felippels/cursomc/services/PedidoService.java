package com.felippels.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.Estado;
import com.felippels.cursomc.domain.Pedido;
import com.felippels.cursomc.repositories.PedidoRepository;
import com.felippels.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repositoryPedido;
	
	public Pedido find(Integer id) {
	Pedido pedido = repositoryPedido.findOne(id);	 
	if (pedido==null) {
		throw new ObjectNotFoundException("Busca por pedido não retornou resulado! id: " + id.toString() +", Tipo:" + Pedido.class.getName()); 
	}
		return pedido;
	}

}
