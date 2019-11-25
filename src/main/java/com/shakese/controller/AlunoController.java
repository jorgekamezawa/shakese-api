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

import com.shakese.controller.dto.AlunoDtoDetalhado;
import com.shakese.controller.dto.AlunoDto;
import com.shakese.controller.form.AlunoFormAtualizar;
import com.shakese.controller.form.AlunoForm;
import com.shakese.modelo.Aluno;
import com.shakese.modelo.Nivel;
import com.shakese.repository.AlunoRepository;
import com.shakese.repository.TurmaRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

//	@Autowired
//	private AlunoRepository alunoRepository;
//
//	@Autowired
//	private TurmaRepository aulaRepository;
//
//	@GetMapping
//	public List<AlunoDto> listAluno(Nivel nivelAula) {
//		if (nivelAula == null) {
//			List<Aluno> aluno = alunoRepository.findAll();
//			return AlunoDto.converter(aluno);
//		} else {
//			List<Aluno> aluno = alunoRepository.findByAulaNivel(nivelAula);
//			return AlunoDto.converter(aluno);
//		}
//	}
//
//	@PostMapping
//	@Transactional
//	public ResponseEntity<AlunoDto> cadastrarAluno(@RequestBody @Valid AlunoForm cadastroAlunoForm,
//			UriComponentsBuilder uriBuilder) {
//		alunoRepository.save(cadastroAlunoForm.converter(aulaRepository));
//
//		URI uri = uriBuilder.path("/alunos/{id}")
//				.buildAndExpand(cadastroAlunoForm.converter(aulaRepository).getAlunoId()).toUri();
//		return ResponseEntity.created(uri).body(new AlunoDto(cadastroAlunoForm.converter(aulaRepository)));
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<AlunoDtoDetalhado> detalharAluno(@PathVariable Long id) {
//		Optional<Aluno> aluno = alunoRepository.findById(id);
//
//		if (aluno.isPresent()) {
//			return ResponseEntity.ok(new AlunoDtoDetalhado(aluno.get()));
//		}
//		return ResponseEntity.notFound().build();
//	}
//
//	@PutMapping("/{id}")
//	@Transactional
//	public ResponseEntity<AlunoDto> atualizarAluno(@PathVariable Long id,
//			@RequestBody @Valid AlunoFormAtualizar atualizaAlunoForm) {
//		Optional<Aluno> optional = alunoRepository.findById(id);
//
//		if (optional.isPresent()) {
//			Aluno aluno = atualizaAlunoForm.atualizar(id, alunoRepository);
//			return ResponseEntity.ok(new AlunoDto(aluno));
//		}
//		return ResponseEntity.notFound().build();
//	}
//
//	@DeleteMapping("/{id}")
//	@Transactional
//	public ResponseEntity<?> deletarAluno(@PathVariable Long id) {
//		Optional<Aluno> optional = alunoRepository.findById(id);
//		if (optional.isPresent()) {
//			alunoRepository.deleteById(id);
//			return ResponseEntity.ok().build();
//		}
//		return ResponseEntity.notFound().build();
//	}
}
