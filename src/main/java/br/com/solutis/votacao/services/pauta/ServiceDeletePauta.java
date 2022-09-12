package br.com.solutis.votacao.services.pauta;

import br.com.solutis.votacao.exceptions.DataIntegrityException;
import br.com.solutis.votacao.repositories.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeletePauta {

	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private ServiceFindPautas serviceFindPautas;
	
	public void delete(Integer id) {
		serviceFindPautas.findById(id);
		try {
			pautaRepository.deleteById(id);
		} catch (Exception e) {
			throw new DataIntegrityException("Não é possível remover uma Pauta com uma Sessão Relacionada.");
		}
	}
}
