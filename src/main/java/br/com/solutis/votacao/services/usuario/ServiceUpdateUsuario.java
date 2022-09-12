package br.com.solutis.votacao.services.usuario;

import br.com.solutis.votacao.domain.Usuario;
import br.com.solutis.votacao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUpdateUsuario {

	@Autowired
	private ServiceFindUsuarios serviceFindUsuarios;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Usuario update(Usuario usuario) {
		serviceFindUsuarios.findById(usuario.getId());
		return usuarioRepository.save(usuario);
	}
}
