package br.com.solutis.votacao.services.pauta;

import br.com.solutis.votacao.domain.Pauta;
import br.com.solutis.votacao.domain.enums.ResultadoVotacao;
import br.com.solutis.votacao.domain.enums.StatusPauta;
import br.com.solutis.votacao.domain.enums.StatusSessao;
import br.com.solutis.votacao.dtos.PautaDTO;
import br.com.solutis.votacao.dtos.SessaoVotacaoDTO;
import br.com.solutis.votacao.exceptions.ObjectNotFoundException;
import br.com.solutis.votacao.repositories.PautaRepository;
import br.com.solutis.votacao.services.sessao.ServiceFindSessaoVotacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceFindPautas {

	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private ServiceFindSessaoVotacao serveFindSessaoVotacao;
	
	public List<Pauta> findAll(){
		return pautaRepository.findAll();
	}
	
	public Pauta findById(Integer id) {
		Optional<Pauta> pauta = pautaRepository.findById(id);
		return pauta.orElseThrow(() -> new ObjectNotFoundException("Pauta não encontrada! ID: " + id + ", Tipo: " + Pauta.class.getSimpleName()));

	}
	
	public List<PautaDTO> findAllDTO(){

		List<Pauta> listPautas = this.findAll();

		List<PautaDTO> listPautaDTO = new ArrayList<>();

		for(Pauta pauta : listPautas) {
			listPautaDTO.add(findByIdDTO(pauta.getId()));
		}

		return listPautaDTO;
	}
	
	public PautaDTO findByIdDTO(Integer id) {
		PautaDTO pautaDTO = new PautaDTO(findById(id));

		try {
			pautaDTO.setSessaoVotacaoDTO(serveFindSessaoVotacao.findByDTO(id));
			processaStatusPauta(pautaDTO);

		} catch (ObjectNotFoundException e) {
			pautaDTO.setSessaoVotacaoDTO(null);
		}

		return pautaDTO;
	}
	
	private void processaStatusPauta(PautaDTO pautaDTO) {
		
		if(pautaDTO.getSessaoVotacaoDTO().getStatusSessao().equals(StatusSessao.VOTACAO_ABERTA)) {
			pautaDTO.setStatusPauta(StatusPauta.ABERTA);
		} else {
			pautaDTO.setStatusPauta(StatusPauta.CONCLUÍDA);
			pautaDTO.setResultadoVotacao(processaResultadoVotacao(pautaDTO.getSessaoVotacaoDTO()));
		}
	}
	
	private ResultadoVotacao processaResultadoVotacao(SessaoVotacaoDTO sessaoVotacaoDTO) {
		
		if(sessaoVotacaoDTO.getQtdSim() == sessaoVotacaoDTO.getQtdNao()) {
			return ResultadoVotacao.EMPATE;
		} else if (sessaoVotacaoDTO.getQtdSim() > sessaoVotacaoDTO.getQtdNao()){
			return ResultadoVotacao.SIM;
		} else {
			return ResultadoVotacao.NAO;
		}
	}
}
