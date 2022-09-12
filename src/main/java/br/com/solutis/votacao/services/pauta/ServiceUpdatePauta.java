package br.com.solutis.votacao.services.pauta;


import br.com.solutis.votacao.domain.Pauta;
import br.com.solutis.votacao.domain.enums.StatusPauta;
import br.com.solutis.votacao.exceptions.ValidationsException;
import br.com.solutis.votacao.repositories.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUpdatePauta {

	@Autowired
	private ServiceFindPautas serviceFindPautas;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	
	public Pauta update(Pauta pautaUpdated) {
		
		Pauta pautaSaved = serviceFindPautas.findById(pautaUpdated.getId());
		
		if(pautaSaved.getStatusPauta().equals(StatusPauta.ABERTA)) {
			
			updateAtributtes(pautaSaved, pautaUpdated);
			return pautaRepository.save(pautaSaved);
			
		} else {
		
			throw new ValidationsException("A Pauta ID: " + pautaSaved.getId() + "está concluída e não pode ser alterada.");
		}
	}
	
	
	private void updateAtributtes(Pauta pautaSaved, Pauta pautaUpdated) {
		pautaSaved.setDescricao(pautaUpdated.getDescricao());
	}

}
