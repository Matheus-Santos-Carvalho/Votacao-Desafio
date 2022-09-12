package br.com.solutis.votacao.controllers;

import br.com.solutis.votacao.domain.Voto;
import br.com.solutis.votacao.dtos.VotoDTO;
import br.com.solutis.votacao.services.voto.ServiceCreateVoto;
import br.com.solutis.votacao.services.voto.ServiceFindVotos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/votos")
public class ControllerVoto {

	@Autowired
	private ServiceFindVotos serviceFindVotos;
	
	@Autowired
	private ServiceCreateVoto serviceCreateVoto;

	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Voto>> findAll(){
		List<Voto> listVotos = serviceFindVotos.findAllVotos();
		return ResponseEntity.ok().body(listVotos);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Voto> findById(@PathVariable Integer id){
		Voto voto = serviceFindVotos.findById(id);
		return ResponseEntity.ok().body(voto);
	}
	
	@RequestMapping(value="/sessaousuario", method=RequestMethod.GET)
	public ResponseEntity<Voto> findAllBySessaoVotacaoIdAndUsuarioId(
			@RequestParam(required = true) Integer id_sessao, 
			@RequestParam(required = true) Integer id_usuario
	){
		Voto voto = serviceFindVotos.findBySessaoVotacaoIdAndUsuarioId(id_sessao, id_usuario);
		return ResponseEntity.ok().body(voto);
	}
	
	@RequestMapping(value="/usuario", method=RequestMethod.GET)
	public ResponseEntity<List<Voto>> findAllVotosByIdUsuario(
			@RequestParam(required = true) Integer id_usuario
	){
		List<Voto> listVotos = serviceFindVotos.findAllByUsuarioId(id_usuario);
		return ResponseEntity.ok().body(listVotos);
	}
	
	@RequestMapping(value="/sessao", method=RequestMethod.GET)
	public ResponseEntity<List<Voto>> findAllByIdSessao(
			@RequestParam(required = true) Integer id_sessao
	){
		List<Voto> listVotos = serviceFindVotos.findAllBySessaoVotacaoId(id_sessao);
		return ResponseEntity.ok().body(listVotos);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody VotoDTO votoDTO){
		serviceCreateVoto.create(votoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(votoDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
