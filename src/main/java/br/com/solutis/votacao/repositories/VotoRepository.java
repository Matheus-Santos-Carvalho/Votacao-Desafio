package br.com.solutis.votacao.repositories;


import br.com.solutis.votacao.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer>{

	List<Voto> findAllBySessaoVotacaoId(Integer id_sessao);
	List<Voto> findAllByUsuarioId(Integer id_usuario);
	Voto findBySessaoVotacaoIdAndUsuarioId(Integer id_sessao, Integer id_usuario);
	
}