package com.shakese.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Pessoa;
import com.shakese.modelo.Professor;
import com.shakese.modelo.Turma;
import com.shakese.repository.ProfessorRepository;
import com.shakese.repository.TurmaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorFormAtualizar {

	@NotNull
	private Pessoa pessoa;
	
	@NotNull
	private double salario;
	
	@NotNull
	private List<Long> idTurmas;
	
	public Professor atualizar(Long id, ProfessorRepository professorRepository,
			TurmaRepository turmaRepository) {
		List<Turma> turmas = turmaRepository.findAll();
		
		List<Turma> turmasProfessor = new ArrayList<Turma>();
		
		for (Long idTurma : idTurmas) {
			Optional<Turma> turma = turmaRepository.findById(idTurma);
			turmasProfessor.add(turma.get());
		}
		
		int validar = 0;
		
		for (Turma turma : turmasProfessor) {
			for (Turma turma2 : turmas) {
				if(turma.isStatus() && turma.getTurmaId() == turma2.getTurmaId()) {
					validar++;
				}
			}
		}
		
		if(validar >= turmasProfessor.size()) {
			Optional<Professor> professor = professorRepository.findById(id);
			professor.get().setPessoa(pessoa);
			professor.get().setSalario(salario);
			professor.get().setTurmas(turmasProfessor);
			
			return professor.get();
		}
		
		return null;
	}
}
