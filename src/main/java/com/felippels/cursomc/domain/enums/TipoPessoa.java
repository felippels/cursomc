package com.felippels.cursomc.domain.enums;

public enum TipoPessoa {
	
	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2,"Pessoa Jurídica");

	private int cod;
	private String descricao;
	
	private TipoPessoa() {}
	
	private TipoPessoa(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
	public static TipoPessoa toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		for (TipoPessoa pessoa : TipoPessoa.values()) {
			if (cod.equals(pessoa.cod)) {
				return pessoa;
			}
			
		}
		
		throw new IllegalArgumentException("Cod inválido" + cod.toString());
	} 
}
