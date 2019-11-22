package com.shakese.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shakese.controller.dto.AulaDto;
import com.shakese.controller.form.AtualizarAulaForm;
import com.shakese.controller.form.AulaForm;
import com.shakese.modelo.Aula;
import com.shakese.repository.AulaRepository;
import com.shakese.repository.NivelRepository;

@RestController
@RequestMapping("/aulas")
public class AulaController {

	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private NivelRepository nivelRepository;

	@GetMapping
	public List<AulaDto> listarAulas() {
		List<Aula> aula = aulaRepository.findAll();
		return AulaDto.converter(aula);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AulaDto> listaDetalhada(@PathVariable Long id){
		Optional<Aula> aula = aulaRepository.findById(id);
		if(aula.isPresent()) {
			return ResponseEntity.ok(new AulaDto(aula.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AulaDto> cadastrarAulas(@RequestBody @Valid AulaForm aulasForm,
			UriComponentsBuilder uriBuilder) {
		aulaRepository.save(aulasForm.converter(nivelRepository));

		URI uri = uriBuilder.path("/aulas/{id}").buildAndExpand(aulasForm.converter(nivelRepository).getAulaId()).toUri();
		return ResponseEntity.created(uri).body(new AulaDto(aulasForm.converter(nivelRepository)));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AulaDto> atualizarAula(@PathVariable Long id,
			@RequestBody @Valid AtualizarAulaForm atualizarAulaForm){
		Optional<Aula> optional = aulaRepository.findById(id);
		
		if (optional.isPresent()) {
			Aula aula = atualizarAulaForm.atualizar(id, aulaRepository);
			return ResponseEntity.ok(new AulaDto(aula));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarAula(@PathVariable Long id){
		Optional<Aula> optional = aulaRepository.findById(id);
		if(optional.isPresent()) {
			aulaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
