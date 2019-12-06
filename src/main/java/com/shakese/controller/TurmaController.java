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
import com.shakese.service.IAlunoService;
import com.shakese.service.IAulaService;
import com.shakese.service.INivelService;
import com.shakese.service.IProfessorService;
import com.shakese.service.ITurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {

	@Autowired
	private ITurmaService turmaService;
	@Autowired
	private IAulaService aulaService;
	@Autowired
	private INivelService nivelService;
	@Autowired
	private IAlunoService alunoService;
	@Autowired
	private IProfessorService professorService;
//	@Autowired
//	private CalendarioRepository calendarioRepository;

	@GetMapping
	public List<TurmaDto> listarTurmas() {
		List<Turma> turmas = turmaService.findAll();
		return TurmaDto.converter(turmas.stream().filter(Turma::isStatus).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<TurmaDtoDetalhada> listarTurma(@PathVariable Long id) {
		Optional<Turma> optional = turmaService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			return ResponseEntity.ok(new TurmaDtoDetalhada(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TurmaDto> cadastrarAulas(@RequestBody @Valid TurmaForm form,
			UriComponentsBuilder uriBuilder) {
		Turma turma = form.converter(aulaService, nivelService);

		if (turma.getAula() != null) {
			List<TurmaDto> turmas = listarTurmas();

			boolean disponibilidade = true;
			for (TurmaDto turmaDto : turmas) {
				if (turma.getNivel().getNome() == turmaDto.getNivelNome()
						&& turma.getAula().getNome() == turmaDto.getAulaNome()) {
					disponibilidade = false;

					break;
				}
			}

			if (disponibilidade) {
				turmaService.save(turma);
//				calendarioRepository.save(turma.getCalendario());

				URI uri = uriBuilder.path("/aulas/{id}").buildAndExpand(turma.getTurmaId()).toUri();
				return ResponseEntity.created(uri).body(new TurmaDto(turma));
			}
			return null;
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TurmaDto> atualizarAula(@PathVariable Long id, @RequestBody @Valid TurmaFormAtualizar form) {
		Optional<Turma> optional = turmaService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			Turma turma = form.atualizar(id, turmaService, aulaService, nivelService);
			return ResponseEntity.ok(new TurmaDto(turma));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarAula(@PathVariable Long id) {
		Optional<Turma> optional = turmaService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {

			turmaService.deletarTurmaAluno(alunoService, optional, id);
			turmaService.deletarTurmaProfessor(professorService, optional, id);

			optional.get().setStatus(false);
			optional.get().setNivel(null);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
