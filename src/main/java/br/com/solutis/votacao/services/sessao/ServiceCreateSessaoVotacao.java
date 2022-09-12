package br.com.solutis.votacao.services.sessao;

import br.com.solutis.votacao.domain.Pauta;
import br.com.solutis.votacao.domain.SessaoVotacao;
import br.com.solutis.votacao.exceptions.ValidationsException;
import br.com.solutis.votacao.repositories.SessaoVotacaoRepository;
import br.com.solutis.votacao.services.pauta.ServiceFindPautas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServiceCreateSessaoVotacao {
	
	String prazoPadraoSessao = "00:01";
	
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private ServiceFindPautas serviceFindPautas;
	
	
	public SessaoVotacao create(Integer idPauta, String prazoSessao) {

		if (checkPautaAlreadyHasSesssao(idPauta)) {

			if ( ! prazoSessao.equals("") && ! prazoSessao.equals("00:00") ) {
				checkFormatPrazoSessao(prazoSessao);
			} else {
				prazoSessao = prazoPadraoSessao;
			}

			SessaoVotacao sessaoVotacao = new SessaoVotacao(
					null,
					LocalDateTime.now(),
					prazoSessao,
					serviceFindPautas.findById(idPauta)
			);

			return sessaoVotacaoRepository.save(sessaoVotacao);
		}

		return null;
	}
	
	
	private boolean checkFormatPrazoSessao(String prazoSessao) {

		if (prazoSessao.length() != 5) {
			throw new ValidationsException(
					"O prazo da Sessão de Votação precisa conter 5 caracteres no seguinte formato: HH:mm.");
		}

		if (!prazoSessao.contains(":")) {
			throw new ValidationsException(
					"O prazo da Sessão de Votação precisa conter o sinal de dois pontos entre as horas e os minutos no seguinte formato: HH:mm.");
		}

		String[] listPrazoSessao = prazoSessao.split(":");
		if (!listPrazoSessao[0].matches("[0-9]*")) {
			throw new ValidationsException(
					"No prazo da Sessão de Votação as horas precisam conter um valor numérico entre 0 e 9 no seguinte formato: HH:mm.");
		}

		if ((!listPrazoSessao[1].matches("[0-9]*"))
				| (Integer.parseInt(listPrazoSessao[1]) > 59 | Integer.parseInt(listPrazoSessao[1]) < 00)) {
			throw new ValidationsException(
					"No prazo da Sessão de Votação os minutos precisam conter até dois algarismos entre 0 e 9 e ser um número entre 00 e 59 no seguinte formato HH:mm.");
		}

		if (prazoSessao == "00:00") {
			throw new ValidationsException(
					"Não é possível iniciar um sessão com o um prazo de 00:00. Caso um prazo válido não seja informado, a sessão terá a duração de 1 minuto.");
		}

		return true;
	}
	
	
	private boolean checkPautaAlreadyHasSesssao(Integer idPauta) {
		
		Pauta pauta = serviceFindPautas.findById(idPauta);
		
		if(pauta.getSessaoVotacao() != null) {
			throw new ValidationsException("A Pauta ID: " + idPauta + " já possui uma Sessão de Votação associada. Para iniciar uma Sessao de Votação informe outra Pauta." );
		}
		
		return true;
	}
}

