package br.com.solutis.votacao.services.pauta;

import br.com.solutis.votacao.domain.Pauta;
import br.com.solutis.votacao.domain.enums.StatusPauta;
import br.com.solutis.votacao.dtos.PautaDTO;
import br.com.solutis.votacao.exceptions.ValidationsException;
import br.com.solutis.votacao.repositories.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ServiceCreatePauta {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public Pauta create(Pauta pauta){
		
		if(checkIfPautaAlreadyExists(pauta)) {
			return pautaRepository.save(pauta);
		}
		
		return null;
		
	}
	
	
	public Pauta fromDTOCreate(PautaDTO objDTO) {
		
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		
		return new Pauta(
				null,
				objDTO.getDescricao(),
				StatusPauta.ABERTA, 
				dataHoraAtual
		);
	}
	
	
	private boolean checkIfPautaAlreadyExists(Pauta pauta){
		
		Pauta pautaSaved = pautaRepository.findPautaByDescricao(pauta.getDescricao());
		
		if(pautaSaved != null) {
			throw new ValidationsException("Já existe uma Pauta com a mesma descrição. ID da Pauta: " + pautaSaved.getId());
		}
		
		return true;
		
	}
}
