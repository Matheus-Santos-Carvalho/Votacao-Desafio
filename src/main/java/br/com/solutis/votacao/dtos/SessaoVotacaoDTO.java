package br.com.solutis.votacao.dtos;

import br.com.solutis.votacao.domain.SessaoVotacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import br.com.solutis.votacao.domain.enums.StatusSessao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter

public class SessaoVotacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHoraInicioSessao;

	private String tempoPrazo;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHoraFinalSessao;
	private StatusSessao statusSessao;

	private long qtdSim;
	private long qtdNao;


	public SessaoVotacaoDTO(SessaoVotacao sessaoVotacao) {

		this.id = sessaoVotacao.getId();
		this.dataHoraInicioSessao = sessaoVotacao.getDataHoraInicioSessao();
		this.tempoPrazo = sessaoVotacao.getTempoPrazo();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataHoraInicioSessao() {
		return dataHoraInicioSessao;
	}

	public void setDataHoraInicioSessao(LocalDateTime dataHoraInicioSessao) {
		this.dataHoraInicioSessao = dataHoraInicioSessao;
	}

	public String getTempoPrazo() {
		return tempoPrazo;
	}

	public void setTempoPrazo(String tempoPrazo) {
		this.tempoPrazo = tempoPrazo;
	}

	public LocalDateTime getDataHoraFinalSessao() {
		return dataHoraFinalSessao;
	}

	public void setDataHoraFinalSessao(LocalDateTime dataHoraFinalSessao) {
		this.dataHoraFinalSessao = dataHoraFinalSessao;
	}


	public StatusSessao getStatusSessao() {
		return statusSessao;
	}

	public void setStatusSessao(StatusSessao statusSessao) {
		this.statusSessao = statusSessao;
	}

	public long getQtdSim() {
		return qtdSim;
	}

	public void setQtdSim(long qtdSim) {
		this.qtdSim = qtdSim;
	}

	public long getQtdNao() {
		return qtdNao;
	}

	public void setQtdNao(long qtdNao) {
		this.qtdNao = qtdNao;
	}
}



