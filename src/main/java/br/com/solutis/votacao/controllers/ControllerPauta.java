package br.com.solutis.votacao.controllers;

import br.com.solutis.votacao.domain.Pauta;
import br.com.solutis.votacao.dtos.PautaDTO;
import br.com.solutis.votacao.services.pauta.ServiceCreatePauta;
import br.com.solutis.votacao.services.pauta.ServiceDeletePauta;
import br.com.solutis.votacao.services.pauta.ServiceFindPautas;
import br.com.solutis.votacao.services.pauta.ServiceUpdatePauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/pautas")
public class ControllerPauta {

	
	@Autowired
	private ServiceFindPautas serviceFindPautas;
	
	@Autowired
	private ServiceCreatePauta serviceCreatePauta;
	
	@Autowired
	private ServiceDeletePauta serviceDeletePauta;
	
	@Autowired
	private ServiceUpdatePauta serviceUpdatePauta;
	
	

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PautaDTO>> findAll(){
		List<PautaDTO> listPautasDTO = serviceFindPautas.findAllDTO();
		return ResponseEntity.ok().body(listPautasDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PautaDTO> find(@PathVariable Integer id){
		PautaDTO PautaDTO = serviceFindPautas.findByIdDTO(id);
		return ResponseEntity.ok().body(PautaDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PautaDTO objDTO){
		Pauta obj = serviceCreatePauta.fromDTOCreate(objDTO);
		obj = serviceCreatePauta.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PautaDTO pautaDTO, @PathVariable Integer id){
		Pauta pauta = new Pauta(id, pautaDTO.getDescricao());
		serviceUpdatePauta.update(pauta);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		serviceDeletePauta.delete(id);
		return ResponseEntity.noContent().build();
	}
}
