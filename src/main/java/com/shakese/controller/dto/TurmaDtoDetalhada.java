package com.shakese.controller.dto;

import com.shakese.modelo.Turma;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDtoDetalhada {

	private Long turmaId;
	private String aulaNome;
	private String nivelNome;
	private double preco;
	//private Professor professor;
	
	public TurmaDtoDetalhada(Turma turma) {
		this.turmaId = turma.getTurmaId();
		this.aulaNome = turma.getAula().getNome();
		this.nivelNome = turma.getNivel().getNome();
		this.preco = turma.getPreco();
//		this.professor = turma.getProfessor();
	}
	
}
