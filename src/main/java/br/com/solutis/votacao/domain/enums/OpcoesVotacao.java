package br.com.solutis.votacao.domain.enums;

public enum OpcoesVotacao {

	SIM(1, "Sim"),
	NAO(0, "Não");
	
	private int cod;
	private String descricao;
	
	private OpcoesVotacao(int cod, String descricao) {
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

	public static OpcoesVotacao toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(OpcoesVotacao x : OpcoesVotacao.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
