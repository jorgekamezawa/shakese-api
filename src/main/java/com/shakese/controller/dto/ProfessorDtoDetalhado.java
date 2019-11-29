package com.shakese.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Pessoa;
import com.shakese.modelo.Professor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDtoDetalhado {
	
	private Long id;
	private Pessoa pessoa;
	private double salario;
	private List<TurmaDto> turmas;
	
	public ProfessorDtoDetalhado (Professor professor) {
		this.id = professor.getProfessorId();
		this.pessoa = professor.getPessoa();
		this.salario = professor.getSalario();
		this.turmas = new ArrayList<TurmaDto>();
		this.turmas.addAll(professor.getTurmas().stream().map(TurmaDto::new)
				.collect(Collectors.toList()));
	}

}
