package br.com.solutis.votacao.services.usuario;


import br.com.solutis.votacao.domain.Usuario;
import br.com.solutis.votacao.dtos.UsuarioDTO;
import br.com.solutis.votacao.exceptions.ValidationsException;
import br.com.solutis.votacao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCreateUsuario {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ServiceFindUsuarios serviceFindUsuarios;
	
	
	public Usuario create(Usuario usuario){
		return usuarioRepository.save(usuario);
	}	
	
	
	public Usuario fromDTO(UsuarioDTO usuarioDTO) {
		
		checkAttributesUsuario(usuarioDTO);
		
		Usuario usuario = new Usuario(
				null,
				usuarioDTO.getNome(),
				usuarioDTO.getCpf(),
				usuarioDTO.getEmail()
		);
		
		return usuario = create(usuario);
		
	}
	
	private void checkAttributesUsuario(UsuarioDTO usuarioDTO){
		
		if(usuarioDTO.getNome() == null | usuarioDTO.getNome().equals("")) {
			throw new ValidationsException("O nome do usuário precisa ser informado.");
		}
		
		if(usuarioDTO.getNome().split(" ").length <= 1) {
			throw new ValidationsException("O 'Nome' deve possuir mais de uma palavra.");
		}
		
		if(usuarioDTO.getEmail() == null | usuarioDTO.getEmail().equals("")) {
			throw new ValidationsException("O Email do usuário precisa ser informado.");
		}
		
		if(!usuarioDTO.getEmail().contains("@")) {
			throw new ValidationsException("Um Email válido precisa ser informado.");
		}
		
		Usuario savedUsuario = serviceFindUsuarios.findByEmail(usuarioDTO.getEmail());
		if(savedUsuario != null) {
			throw new ValidationsException("Ja existe um usuário cadastrado com este Email.");
		}
		

	}
	

}
