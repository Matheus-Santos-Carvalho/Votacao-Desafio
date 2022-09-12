package br.com.solutis.votacao.services.voto;

import br.com.solutis.votacao.domain.Voto;
import br.com.solutis.votacao.exceptions.ObjectNotFoundException;
import br.com.solutis.votacao.repositories.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceFindVotos {

	@Autowired
	private VotoRepository votoRepository;
	
	public List<Voto> findAllVotos(){
		return votoRepository.findAll();
	}
	
	public Voto findById(Integer id) {
		Optional<Voto> voto = votoRepository.findById(id);
		return voto.orElseThrow(() -> new ObjectNotFoundException("Pauta não encontrada! ID: " + id + ", Tipo: " + Voto.class.getSimpleName()));

	}
	
	public List<Voto> findAllBySessaoVotacaoId(Integer idSessaoVotacao) {
		return votoRepository.findAllBySessaoVotacaoId(idSessaoVotacao);
	}
	
	public Voto findBySessaoVotacaoIdAndUsuarioId(Integer id_sessao, Integer id_usuario) {
		return votoRepository.findBySessaoVotacaoIdAndUsuarioId(id_sessao, id_usuario);
	}
	
	public List<Voto> findAllByUsuarioId(Integer id_usuario) {
		return votoRepository.findAllByUsuarioId(id_usuario);
		
	}
}
