package br.com.solutis.votacao.controllers;

import br.com.solutis.votacao.domain.SessaoVotacao;
import br.com.solutis.votacao.dtos.SessaoVotacaoDTO;
import br.com.solutis.votacao.services.sessao.ServiceCreateSessaoVotacao;
import br.com.solutis.votacao.services.sessao.ServiceDeleteSessaoVotacao;
import br.com.solutis.votacao.services.sessao.ServiceFindSessaoVotacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/sessao")
public class ControllerSessao {

	@Autowired
	private ServiceFindSessaoVotacao serviceFindSessaoVotacao;
	
	@Autowired
	private ServiceCreateSessaoVotacao serviceCreateSessaoVotacao;
	
	@Autowired
	private ServiceDeleteSessaoVotacao serviceDeleteSessaoVotacao;
		
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SessaoVotacaoDTO>> findAll(){
		List<SessaoVotacaoDTO> listSessaoVotacaoDTO = serviceFindSessaoVotacao.findAllDTO();
		return ResponseEntity.ok().body(listSessaoVotacaoDTO);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<SessaoVotacaoDTO> findById(@PathVariable Integer id){
		SessaoVotacaoDTO sessaoVotacaoDTO = serviceFindSessaoVotacao.findByDTO(id);
		return ResponseEntity.ok().body(sessaoVotacaoDTO);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestParam(required = true) Integer idPauta, String prazoSessao){
		SessaoVotacao sessaoVotacao = serviceCreateSessaoVotacao.create(idPauta, prazoSessao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sessaoVotacao.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		serviceDeleteSessaoVotacao.delete(id);
		return ResponseEntity.noContent().build();
	}
}
