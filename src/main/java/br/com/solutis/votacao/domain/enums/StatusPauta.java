package br.com.solutis.votacao.domain.enums;

public enum StatusPauta {

	ABERTA(1, "Aberta"),
	CONCLUÍDA(2, "Concluída");
	
	private int cod;
	private String descricao;
	
	private StatusPauta(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusPauta toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(StatusPauta x : StatusPauta.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
