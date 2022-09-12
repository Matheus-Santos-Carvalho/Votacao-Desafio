package br.com.solutis.votacao.repositories;

import br.com.solutis.votacao.domain.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Integer>{

}
