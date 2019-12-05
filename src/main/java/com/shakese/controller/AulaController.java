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

import com.shakese.controller.dto.AulaDto;
import com.shakese.controller.dto.AulaDtoDetalhada;
import com.shakese.controller.form.AulaForm;
import com.shakese.controller.form.AulaFormAtualizar;
import com.shakese.modelo.Aula;
import com.shakese.modelo.Turma;
import com.shakese.repository.AulaRepository;
import com.shakese.repository.NivelRepository;
import com.shakese.repository.TurmaRepository;

@RestController
@RequestMapping("/aula")
public class AulaController {

	@Autowired
	private AulaRepository aulaRepository;
	@Autowired
	private NivelRepository nivelRepository;
	@Autowired
	private TurmaRepository turmaRepository;

	@GetMapping
	public List<AulaDto> listarAulas() {
		List<Aula> aulas = aulaRepository.findAll();

		return AulaDto.converter(aulas.stream().filter(Aula::isStatus)
				.collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AulaDtoDetalhada> listarAulaDetalhada(@PathVariable Long id) {
		Optional<Aula> optional = aulaRepository.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			return ResponseEntity.ok(new AulaDtoDetalhada(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AulaDto> cadastrarAula(@RequestBody @Valid AulaForm form, 
			UriComponentsBuilder uriBuilder) {
		Aula aula = form.cadastrar(aulaRepository, nivelRepository);
		
		if (aula.getNiveis() != null) {
			aulaRepository.save(aula);
			URI uri = uriBuilder.path("/aula/{id}").buildAndExpand(aula.getAulaId()).toUri();
			return ResponseEntity.created(uri).body(new AulaDto(aula));
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AulaDto> atualizarAula(@PathVariable Long id, @RequestBody @Valid AulaFormAtualizar form) {
		Optional<Aula> optional = aulaRepository.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			Aula aula = form.atualizar(id, aulaRepository, nivelRepository, turmaRepository);
			return ResponseEntity.ok(new AulaDto(aula));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarAula(@PathVariable Long id) {
		Optional<Aula> optional = aulaRepository.findById(id);
		List<Turma> turmas = turmaRepository.findByAulaNomeAndStatusTrue(optional.get().getNome());
		if (optional.isPresent() && optional.get().isStatus() && turmas.isEmpty()) {
			optional.get().setStatus(false);
			optional.get().setNiveis(null);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
