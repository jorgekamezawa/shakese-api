package com.shakese.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Aula;
import com.shakese.modelo.Nivel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AulaDto {

	private Long AulaId;
	private Nivel nivel;
	private double preco;
	
	public AulaDto(Aula aula) {
		this.AulaId = aula.getAulaId();
		this.nivel = aula.getNivel();
		this.preco = aula.getPreco();
		
		
		
	}

	public static List<AulaDto> converter(List<Aula> aula) {
		return aula.stream().map(AulaDto::new).collect(Collectors.toList());
	}
}
