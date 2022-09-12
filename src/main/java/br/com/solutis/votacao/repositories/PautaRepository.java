package br.com.solutis.votacao.repositories;


import br.com.solutis.votacao.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer>{

	Pauta findPautaByDescricao(String descricao);
	
}
