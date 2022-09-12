package br.com.solutis.votacao.repositories;

import br.com.solutis.votacao.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findByCpf(String cpf);
	Usuario findByEmail(String email);
	
}
