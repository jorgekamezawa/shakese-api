package com.shakese.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Aluno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDto {

	private Long id;
	private String nome;
	private List<TurmaDto> turmas;

	public AlunoDto(Aluno aluno) {
		this.id = aluno.getAlunoId();
		this.turmas = new ArrayList<>();
		this.turmas.addAll(aluno.getTurma().stream()
				.map(TurmaDto::new)
				.collect(Collectors.toList()));
	}

	public static List<AlunoDto> converter(List<Aluno> alunos) {
		return alunos.stream().map(AlunoDto::new).collect(Collectors.toList());
	}
}
