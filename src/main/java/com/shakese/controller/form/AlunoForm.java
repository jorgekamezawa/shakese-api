package com.shakese.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Aula;
import com.shakese.modelo.Pessoa;
import com.shakese.modelo.Turma;
import com.shakese.repository.TurmaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoForm {
	
	@NotNull
	private Pessoa pessoa;
	@NotNull
	private double desconto;
	@NotNull
	private List<Turma> turmas;
	
	public Aluno converter(TurmaRepository turmaRepository) {
		
		
		// Nome das Turmas
		// Pegar as turmas
		// New Aluno (turmas)
		
//		
//		List<Turma> turmasAluno = new ArrayList<Turma>();
//		
//		for (Turma t : turmas) {
//			Aula aula = t.getAula();
//			
//			Turma turma = turmaRepository.findByAula(aula);
//			turmasAluno.add(turmaRepository.findByAula(aula));
//		}

		
		return new Aluno(pessoa, desconto, turmas);
	}
	

}
