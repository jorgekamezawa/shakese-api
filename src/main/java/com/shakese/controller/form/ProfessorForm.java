package com.shakese.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Pessoa;
import com.shakese.modelo.Professor;
import com.shakese.modelo.Turma;
import com.shakese.repository.TurmaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorForm {
	
	@NotNull
	private Pessoa pessoa;
	
	@NotNull
	private double salario;
	
	@NotNull
	private List<Long> idTurmas;
	
	public Professor cadastrar(TurmaRepository turmaRepository) {
		List<Turma> turmasProfessor = new ArrayList<Turma>();
		
		for (Long id : idTurmas) {
			Optional<Turma> turma =turmaRepository.findById(id);
			turmasProfessor.add(turma.get());
		}
		
		return new Professor(pessoa, salario, turmasProfessor);
	}

}