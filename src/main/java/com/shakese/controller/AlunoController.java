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

import com.shakese.controller.dto.AlunoDto;
import com.shakese.controller.dto.AlunoDtoDetalhado;
import com.shakese.controller.form.AlunoForm;
import com.shakese.controller.form.AlunoFormAtualizar;
import com.shakese.modelo.Aluno;
import com.shakese.service.IAlunoService;
import com.shakese.service.IEnderecoService;
import com.shakese.service.ITurmaService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private IAlunoService alunoService;
	@Autowired
	private ITurmaService turmaService;
	@Autowired
	private IEnderecoService enderecoService;

	@GetMapping
	public List<AlunoDto> listarAlunos() {
		List<Aluno> alunos = alunoService.findAll();

		return AlunoDto.converter(alunos.stream().filter(Aluno::isStatus).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoDtoDetalhado> detalharAluno(@PathVariable Long id) {
		Optional<Aluno> optional = alunoService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			return ResponseEntity.ok(new AlunoDtoDetalhado(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AlunoDto> cadastrarAluno(@RequestBody @Valid AlunoForm form,
			UriComponentsBuilder uriBuilder) {
		alunoService.save(form.cadastrar(turmaService));
		enderecoService.save(form.getPessoa().getEndereco());

		URI uri = uriBuilder.path("/aluno/{id}").buildAndExpand(form.cadastrar(turmaService).getAlunoId()).toUri();
		return ResponseEntity.created(uri).body(new AlunoDto(form.cadastrar(turmaService)));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AlunoDto> atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoFormAtualizar form) {
		Optional<Aluno> optional = alunoService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			Aluno aluno = form.atualizar(id, alunoService, turmaService);
			return ResponseEntity.ok(new AlunoDto(aluno));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarAluno(@PathVariable Long id) {
		Optional<Aluno> optional = alunoService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			optional.get().setStatus(false);

			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
