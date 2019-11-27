package com.shakese.controller.form;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Aula;
import com.shakese.modelo.Nivel;
import com.shakese.modelo.Professor;
import com.shakese.modelo.Turma;
import com.shakese.repository.AulaRepository;
import com.shakese.repository.NivelRepository;
import com.shakese.repository.TurmaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaFormAtualizar {
	
	@NotNull
	private Long aulaId;
	
	@NotNull
	private Long nivelId;
	
	@NotNull
	private double preco;
	
	private Professor professor;
	
	private List<Aluno> alunos;
	
	public Turma atualizar(Long id, TurmaRepository turmaRepository, AulaRepository aulaRepository,
			NivelRepository nivelRepository) {
		Optional<Aula> aula = aulaRepository.findById(aulaId);
		Optional<Nivel> nivel = nivelRepository.findById(nivelId);
		Optional<Turma> turma = turmaRepository.findById(id);
		turma.get().setAula(aula.get());
		turma.get().setNivel(nivel.get());
		turma.get().setPreco(this.preco);
//		if(professor != null)
//		turma.get().setProfessor(professor);
//		if(alunos != null)
//		turma.get().setAlunos(alunos);
		return turma.get();
	}

}
