package br.com.solutis.votacao.services.usuario;

import br.com.solutis.votacao.exceptions.DataIntegrityException;
import br.com.solutis.votacao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeleteUsuario {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ServiceFindUsuarios service;
	
	public void delete(Integer id) {
		service.findById(id);
		try {
			usuarioRepository.deleteById(id);
		} catch (Exception e) {
			throw new DataIntegrityException("Não é possível remover um usuário que contenha votos salvos.");
		}
	}

	
}
