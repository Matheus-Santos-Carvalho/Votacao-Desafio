package br.com.solutis.votacao.services.sessao;

import br.com.solutis.votacao.exceptions.DataIntegrityException;
import br.com.solutis.votacao.repositories.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeleteSessaoVotacao {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private ServiceFindSessaoVotacao serviceSessaoVotacao;
	
	public void delete(Integer id) {
		serviceSessaoVotacao.findById(id);
		try {
			sessaoVotacaoRepository.deleteById(id);
		} catch (Exception e) {
			throw new DataIntegrityException("Não é possível remover um usuário que contenha votos salvos.");
		}
	}
}
