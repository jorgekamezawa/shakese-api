package com.shakese.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Aula;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AulaDto {
	
	private Long id;
	private String nome;
	private List<NivelDto> nivel;
	
	public AulaDto(Aula aula) {
		this.id = aula.getAulaId();
		this.nome = aula.getNome();
		this.nivel = new ArrayList<>();
		this.nivel.addAll(aula.getNiveis().stream().map(NivelDto::new).collect(Collectors.toList()));
	}
	
	public static List<AulaDto> converter(List<Aula> aulas) {
		return aulas.stream().map(AulaDto::new).collect(Collectors.toList());
	}
	

}
