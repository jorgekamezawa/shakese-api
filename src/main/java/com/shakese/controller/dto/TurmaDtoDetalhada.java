package com.shakese.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Professor;
import com.shakese.modelo.Turma;

public class TurmaDtoDetalhada {

	private Long turmaId;
	private String aulaNome;
	private String nivelNome;
	private double preco;
	private Professor professor;
	private List<AlunoDto> alunos;
	
	public TurmaDtoDetalhada(Turma turma) {
		this.turmaId = turma.getTurmaId();
		this.aulaNome = turma.getAula().getNome();
		this.nivelNome = turma.getNivel().getNome();
		this.preco = turma.getPreco();
//		this.professor = turma.getProfessor();
//		this.alunos = new ArrayList<>();
//		this.alunos.addAll(turma.getAlunos().stream().map(AlunoDto::new)
//				.collect(Collectors.toList()));
	}
	
}
