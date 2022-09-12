package br.com.solutis.votacao.controllers;

import br.com.solutis.votacao.domain.Usuario;
import br.com.solutis.votacao.dtos.UsuarioDTO;
import br.com.solutis.votacao.services.usuario.ServiceCreateUsuario;
import br.com.solutis.votacao.services.usuario.ServiceDeleteUsuario;
import br.com.solutis.votacao.services.usuario.ServiceFindUsuarios;
import br.com.solutis.votacao.services.usuario.ServiceUpdateUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/usuarios")
public class ControllerUsuario {
	
	@Autowired
	private ServiceFindUsuarios serviceFindUsuarios;
	
	@Autowired
	private ServiceCreateUsuario serviceCreateUsuario;
	
	@Autowired
	private ServiceDeleteUsuario serviceDeleteUsuario;
	
	@Autowired
	private ServiceUpdateUsuario serviceUpdateUsuario;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		List<Usuario> listUsuarios = serviceFindUsuarios.findAll();
		List<UsuarioDTO> listUsuariosDTO = listUsuarios.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listUsuariosDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> findById(@PathVariable Integer id){
		Usuario usuario = serviceFindUsuarios.findById(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO usuarioDTO){
		Usuario usuario = serviceCreateUsuario.fromDTO(usuarioDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable Integer id){
		Usuario usuario = serviceCreateUsuario.fromDTO(usuarioDTO);
		usuario.setId(id);
		usuario = serviceUpdateUsuario.update(usuario);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		serviceDeleteUsuario.delete(id);
		return ResponseEntity.noContent().build();
	}
}
