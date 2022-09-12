package br.com.solutis.votacao.domain;

import br.com.solutis.votacao.domain.enums.OpcoesVotacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Voto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer voto;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHoraVoto;
	
	@ManyToOne
	@JoinColumn(name="idSessao")
	private SessaoVotacao sessaoVotacao;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	public Voto() {
		
	}

	public Voto(Integer id, OpcoesVotacao voto, LocalDateTime dataHoraVoto, SessaoVotacao sessaoVotacao, Usuario usuario) {
		super();
		this.id = id;
		this.voto = voto.getCod();
		this.dataHoraVoto = dataHoraVoto;
		this.sessaoVotacao = sessaoVotacao;
		this.usuario = usuario;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OpcoesVotacao getVoto() {
		return OpcoesVotacao.toEnum(this.voto);
	}

	public void setVoto(Integer voto) {
		this.voto = voto;
	}

	public LocalDateTime getDataHoraVoto() {
		return dataHoraVoto;
	}

	public void setDataHoraVoto(LocalDateTime dataHoraVoto) {
		this.dataHoraVoto = dataHoraVoto;
	}

	public SessaoVotacao getSessaoVotacao() {
		return sessaoVotacao;
	}

	public void setSessaoVotacao(SessaoVotacao sessaoVotacao) {
		this.sessaoVotacao = sessaoVotacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		return Objects.equals(id, other.id);
	}
}
