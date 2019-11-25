package com.shakese.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Turma;
import com.shakese.modelo.Aluno;
import com.shakese.modelo.Aula;
import com.shakese.modelo.Nivel;
import com.shakese.modelo.Professor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDto {

	private Long turmaId;
	private double preco;
	private Aula aula;
	private Professor professor;
	private List<Aluno> alunos;
	
	public TurmaDto(Turma turma) {
		this.turmaId = turma.getTurmaId();
		this.preco = turma.getPreco();
		this.aula = turma.getAula();
		this.professor = turma.getProfessor();
		this.alunos = turma.getAlunos();
	}

	public static List<TurmaDto> converter(List<Turma> aula) {
		return aula.stream().map(TurmaDto::new).collect(Collectors.toList());
	}
}
