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

import com.shakese.controller.dto.TurmaDto;
import com.shakese.controller.form.TurmaForm;
import com.shakese.controller.form.TurmaFormAtualizar;
import com.shakese.modelo.Turma;
import com.shakese.repository.AulaRepository;
import com.shakese.repository.TurmaRepository;

@RestController
@RequestMapping("/turma")
public class TurmaController {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private AulaRepository aulaRepository;

	@GetMapping
	public List<TurmaDto> listarAulas() {
		List<Turma> aula = turmaRepository.findAll();
		return TurmaDto.converter(aula);
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<TurmaDto> listaDetalhada(@PathVariable Long id){
		Optional<Turma> aula = turmaRepository.findById(id);
		if(aula.isPresent()) {
			return ResponseEntity.ok(new TurmaDto(aula.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TurmaDto> cadastrarAulas(@RequestBody @Valid TurmaForm form,
		UriComponentsBuilder uriBuilder) {
		Turma turma = form.converter(aulaRepository);
		if(turma.getAula() != null) {
			turmaRepository.save(turma);
			URI uri = uriBuilder.path("/aulas/{id}").buildAndExpand(turma.getTurmaId()).toUri();
			return ResponseEntity.created(uri).body(new TurmaDto(turma));
		}
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TurmaDto> atualizarAula(@PathVariable Long id,
			@RequestBody @Valid TurmaFormAtualizar form){
		Optional<Turma> optional = turmaRepository.findById(id);
		if (optional.isPresent()) {
			Turma turma = form.atualizar(id, turmaRepository);
			return ResponseEntity.ok(new TurmaDto(turma));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarAula(@PathVariable Long id){
		Optional<Turma> optional = turmaRepository.findById(id);
		if(optional.isPresent()) {
			turmaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
