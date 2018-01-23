package com.felippels.cursomc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.felippels.cursomc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
	
	private Integer numParcelas;

	public PagamentoComCartao() {
		super();
	}

	public PagamentoComCartao(Integer id, EstadoPagamento  estadoPagamento, Pedido pedido, Integer numParcelas) {
		super(id, estadoPagamento, pedido);
		this.numParcelas = numParcelas;

	}

	public PagamentoComCartao(Integer numParcelas) {
		super();
		this.numParcelas = numParcelas;
	}

	public Integer getNumParcelas() {
		return numParcelas;
	}

	public void setNumParcelas(Integer numParcelas) {
		this.numParcelas = numParcelas;
	}
	
	
	

}
