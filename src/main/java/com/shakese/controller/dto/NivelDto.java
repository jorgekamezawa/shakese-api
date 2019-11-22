package com.shakese.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Nivel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NivelDto {
	
	private Long nivelId;
	
	private String nome;

	public NivelDto(Nivel nivel) {
		this.nivelId = nivel.getNivelId();
		this.nome = nivel.getNome();
	}
	
	public static List<NivelDto> converter(List<Nivel> nivel){
		return nivel.stream().map(NivelDto::new).collect(Collectors.toList());
	}
}
