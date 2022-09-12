package br.com.solutis.votacao.services.voto;

import br.com.solutis.votacao.domain.Voto;
import br.com.solutis.votacao.domain.enums.StatusSessao;
import br.com.solutis.votacao.dtos.SessaoVotacaoDTO;
import br.com.solutis.votacao.dtos.VotoDTO;
import br.com.solutis.votacao.exceptions.ValidationsException;
import br.com.solutis.votacao.repositories.VotoRepository;
import br.com.solutis.votacao.services.sessao.ServiceFindSessaoVotacao;
import br.com.solutis.votacao.services.usuario.ServiceFindUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class ServiceCreateVoto {

	private VotoDTO votoDTO;
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private ServiceFindVotos serviceFindVotos;
	
	@Autowired
	private ServiceFindSessaoVotacao serviceFindSessaVotacao;
	
	@Autowired
	private ServiceFindUsuarios serviceFindUsuarios;


	
	
	public Voto create(VotoDTO votoDTO){
		
		this.votoDTO = votoDTO;
		
		if(checkCanVote()) {
			return votoRepository.save(votoFromVotoDTO());
		} else {
			return null;
		}
	}
	
	private Voto votoFromVotoDTO() {
		
		Voto voto = new Voto(
				null, 
				votoDTO.getVoto(), 
				LocalDateTime.now(), 
				serviceFindSessaVotacao.findById(votoDTO.getIdSessaoVotacao()), 
				serviceFindUsuarios.findById(votoDTO.getIdUsuario())
		);
		
		return voto;
		
	}
	
	
	private boolean checkCanVote() {
		

		checkAlreadyVoted();
		checkSessionIsOpen();
		
		return true;
	}
	

	
	
	private boolean checkAlreadyVoted() {
		
		Optional<Voto> computedVote = Optional.ofNullable(
				serviceFindVotos.findBySessaoVotacaoIdAndUsuarioId(this.votoDTO.getIdSessaoVotacao(), this.votoDTO.getIdUsuario())
		);
		
		if(computedVote.isPresent()) {
			throw new ValidationsException(
					"Voto não Enviado! Já existe um voto computado do usuário ID: " + this.votoDTO.getIdUsuario() + " para a Sessão ID: " + this.votoDTO.getIdSessaoVotacao());
		} else {
			return true;
		}
	}
	
	
	private boolean checkSessionIsOpen() {

		SessaoVotacaoDTO sessaoVotacaoDTO = serviceFindSessaVotacao.findByDTO(this.votoDTO.getIdSessaoVotacao());

		if(sessaoVotacaoDTO.getStatusSessao().equals(StatusSessao.VOTACAO_ABERTA)) {
			return true;
		} else {
			throw new ValidationsException("Voto não Enviado! A Sessão de Votação ID: " + this.votoDTO.getIdSessaoVotacao() + " está encerrada.");
		}
	}
}
