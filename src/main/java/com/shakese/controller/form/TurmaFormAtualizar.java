package com.shakese.controller.form;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Aula;
import com.shakese.modelo.Professor;
import com.shakese.modelo.Turma;
import com.shakese.repository.TurmaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaFormAtualizar {
	
	@NotNull
	private Aula aula;
	
	@NotNull
	private double preco;
	
	private Professor professor;
	
	private List<Aluno> alunos;
	
	public Turma atualizar(Long id, TurmaRepository turmaRepository) {
		Optional<Turma> turma = turmaRepository.findById(id);
		turma.get().setAula(this.aula);
		turma.get().setPreco(this.preco);
		if(professor != null)
		turma.get().setProfessor(professor);
		if(alunos != null)
		turma.get().setAlunos(alunos);
		return turma.get();
	}

}
