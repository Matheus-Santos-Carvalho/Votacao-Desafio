package br.com.solutis.votacao.domain.enums;

public enum ResultadoVotacao {

	SIM(1, "Aberta"),
	NAO(2, "Concluída"),
	EMPATE(3, "Empate - Resultado Indefinido");
	
	private int cod;
	private String descricao;
	
	private ResultadoVotacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static ResultadoVotacao toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(ResultadoVotacao x : ResultadoVotacao.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
