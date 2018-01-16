package com.felippels.cursomc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1,"Pedente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");

	private int cod;
	private String descricao;
	
	private EstadoPagamento() {}
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
			if (cod.equals(estadoPagamento.cod)) {
				return estadoPagamento;
			}
			
		}
		
		throw new IllegalArgumentException("Cod inválido" + cod.toString());
	} 
}
