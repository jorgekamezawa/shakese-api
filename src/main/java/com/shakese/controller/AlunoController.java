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

import com.shakese.controller.dto.AlunoDto;
import com.shakese.controller.dto.AlunoDtoDetalhado;
import com.shakese.controller.form.AlunoForm;
import com.shakese.controller.form.AlunoFormAtualizar;
import com.shakese.modelo.Aluno;
import com.shakese.repository.AlunoRepository;
import com.shakese.repository.EnderecoRepository;
import com.shakese.repository.TurmaRepository;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	@GetMapping
	public List<AlunoDto> listAluno() {
		List<Aluno> aluno = alunoRepository.findAll();
		return AlunoDto.converter(aluno);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlunoDtoDetalhado> detalharAluno(@PathVariable Long id) {
		Aluno aluno = alunoRepository.getOne(id);

		if (aluno != null) {
			return ResponseEntity.ok(new AlunoDtoDetalhado(aluno));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AlunoDto> cadastrarAluno(@RequestBody @Valid AlunoForm form,
			UriComponentsBuilder uriBuilder) {
		alunoRepository.save(form.converter(turmaRepository));
		enderecoRepository.save(form.getPessoa().getEndereco());

		URI uri = uriBuilder.path("/aluno/{id}")
				.buildAndExpand(form.converter(turmaRepository).getAlunoId()).toUri();
		return ResponseEntity.created(uri).body(new AlunoDto(form.converter(turmaRepository)));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AlunoDto> atualizarAluno(@PathVariable Long id,
			@RequestBody @Valid AlunoFormAtualizar form) {
		Optional<Aluno> optional = alunoRepository.findById(id);

		if (optional.isPresent()) {
			Aluno aluno = form.atualizar(id, alunoRepository, turmaRepository);
			return ResponseEntity.ok(new AlunoDto(aluno));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarAluno(@PathVariable Long id) {
		Optional<Aluno> optional = alunoRepository.findById(id);
		if (optional.isPresent()) {
			alunoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
