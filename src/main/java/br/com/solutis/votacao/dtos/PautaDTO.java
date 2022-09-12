package br.com.solutis.votacao.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.solutis.votacao.domain.Pauta;
import br.com.solutis.votacao.domain.enums.ResultadoVotacao;
import br.com.solutis.votacao.domain.enums.StatusPauta;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

public class PautaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty
	@Length(min=15, max=140, message="O campo Descrição deve ter entre 15 e 140 caracteres.")
	private String descricao;
	
	private StatusPauta statusPauta;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataCriacaoPauta; 
	
	@JsonProperty("sessaoVotacao")
	private SessaoVotacaoDTO sessaoVotacaoDTO;
	
	private ResultadoVotacao resultadoVotacao;

	
	public PautaDTO(Pauta pauta) {
		this.id = pauta.getId();
		this.descricao = pauta.getDescricao();
		this.statusPauta = pauta.getStatusPauta();
		this.dataCriacaoPauta = pauta.getDataCriacaoPauta();
	}
	
	public PautaDTO(String descricao) {
		this.descricao = descricao;
	}

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatusPauta getStatusPauta() {
		return statusPauta;
	}

	public void setStatusPauta(StatusPauta statusPauta) {
		this.statusPauta = statusPauta;
	}

	public LocalDateTime getDataCriacaoPauta() {
		return dataCriacaoPauta;
	}

	public void setDataCriacaoPauta(LocalDateTime dataCriacaoPauta) {
		this.dataCriacaoPauta = dataCriacaoPauta;
	}

	public SessaoVotacaoDTO getSessaoVotacaoDTO() {
		return sessaoVotacaoDTO;
	}

	public void setSessaoVotacaoDTO(SessaoVotacaoDTO sessaoVotacao) {
		this.sessaoVotacaoDTO = sessaoVotacao;
	}

	public ResultadoVotacao getResultadoVotacao() {
		return resultadoVotacao;
	}

	public void setResultadoVotacao(ResultadoVotacao resultadoVotacao) {
		this.resultadoVotacao = resultadoVotacao;
	}
}
