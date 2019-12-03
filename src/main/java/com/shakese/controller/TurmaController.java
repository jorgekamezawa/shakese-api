package com.shakese.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.shakese.controller.dto.TurmaDtoDetalhada;
import com.shakese.controller.form.TurmaForm;
import com.shakese.controller.form.TurmaFormAtualizar;
import com.shakese.modelo.Turma;
import com.shakese.repository.AulaRepository;
import com.shakese.repository.NivelRepository;
import com.shakese.repository.TurmaRepository;

@RestController
@RequestMapping("/turma")
public class TurmaController {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private NivelRepository nivelRepository;

	@GetMapping
	public List<TurmaDto> listarTurmas() {
		List<Turma> turmas = turmaRepository.findAll();
		return TurmaDto.converter(
				turmas.stream()
				.filter(Turma::isStatus)
				.collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<TurmaDtoDetalhada> listarTurma(@PathVariable Long id){
		Optional<Turma> optional = turmaRepository.findById(id);
		if(optional.isPresent() && optional.get().isStatus()) {
			return ResponseEntity.ok(new TurmaDtoDetalhada(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TurmaDto> cadastrarAulas(@RequestBody @Valid TurmaForm form,
		UriComponentsBuilder uriBuilder) {
		Turma turma = form.converter(aulaRepository, nivelRepository);
		if(turma.getAula() != null) {
			List<TurmaDto> turmas = listarTurmas();
			boolean disponibilidade = true;
			for (TurmaDto turmaDto : turmas) {
				if(turma.getNivel().getNome() == turmaDto.getNivelNome() &&
						turma.getAula().getNome() == turmaDto.getAulaNome()){
							disponibilidade = false;
							break;
						}
			}
			if(disponibilidade) {
				turmaRepository.save(turma);
				URI uri = uriBuilder.path("/aulas/{id}").buildAndExpand(turma.getTurmaId()).toUri();
				return ResponseEntity.created(uri).body(new TurmaDto(turma));
			}
			return ResponseEntity.notFound().build();
		}
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TurmaDto> atualizarAula(@PathVariable Long id,
			@RequestBody @Valid TurmaFormAtualizar form){
		Optional<Turma> optional = turmaRepository.findById(id);
		if (optional.isPresent() && optional.get().isStatus()) {
			Turma turma = form.atualizar(id, turmaRepository, aulaRepository, nivelRepository);
			return ResponseEntity.ok(new TurmaDto(turma));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarAula(@PathVariable Long id){
		Optional<Turma> optional = turmaRepository.findById(id);
		if(optional.isPresent() && optional.get().isStatus()) {
			optional.get().setStatus(false);
			optional.get().setNivel(null);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
