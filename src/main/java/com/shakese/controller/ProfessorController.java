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

import com.shakese.controller.dto.ProfessorDto;
import com.shakese.controller.dto.ProfessorDtoDetalhado;
import com.shakese.controller.form.ProfessorForm;
import com.shakese.controller.form.ProfessorFormAtualizar;
import com.shakese.modelo.Professor;
import com.shakese.service.IEnderecoService;
import com.shakese.service.IProfessorService;
import com.shakese.service.ITurmaService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	private IProfessorService professorService;
	@Autowired
	private ITurmaService turmaService;
	@Autowired
	private IEnderecoService enderecoService;

	@GetMapping
	public List<ProfessorDto> listarProfessores() {
		List<Professor> professores = professorService.findAll();

		return ProfessorDto.converter(professores.stream().filter(Professor::isStatus).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProfessorDtoDetalhado> listarProfessor(@PathVariable Long id) {
		Optional<Professor> optional = professorService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			return ResponseEntity.ok(new ProfessorDtoDetalhado(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProfessorDto> cadastrarProfessor(@RequestBody @Valid ProfessorForm form,
			UriComponentsBuilder uriBuilder) {
		professorService.save(form.cadastrar(turmaService));
		enderecoService.save(form.getPessoa().getEndereco());

		URI uri = uriBuilder.path("/professor/{id}").buildAndExpand(form.cadastrar(turmaService).getProfessorId())
				.toUri();
		return ResponseEntity.created(uri).body(new ProfessorDto(form.cadastrar(turmaService)));

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProfessorDto> atualizarProfessor(@PathVariable Long id,
			@RequestBody @Valid ProfessorFormAtualizar form) {
		Optional<Professor> optional = professorService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			Professor professor = form.atualizar(id, professorService, turmaService);
			
			return ResponseEntity.ok(new ProfessorDto(professor));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarProfessor(@PathVariable Long id) {
		Optional<Professor> optional = professorService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			optional.get().setStatus(false);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
