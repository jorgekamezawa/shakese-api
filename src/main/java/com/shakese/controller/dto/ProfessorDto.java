package com.shakese.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Professor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDto {
	
	private Long id;
	private String nome;
	private String celular;
	
	public ProfessorDto (Professor professor) {
		this.id = professor.getProfessorId();
		this.nome = professor.getPessoa().getNome();
		this.celular = professor.getPessoa().getCelular();
	}
	
	public static List<ProfessorDto> converter(List<Professor> professor){
		return professor.stream().map(ProfessorDto::new).collect(Collectors.toList());
	}

}
