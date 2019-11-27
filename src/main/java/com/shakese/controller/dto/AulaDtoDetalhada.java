package com.shakese.controller.dto;

import java.util.ArrayList;
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
public class AulaDtoDetalhada {

	private Long aulaId;
	private String nome;
	private List<NivelDto> nivel;
	private List<TurmaDto> turmas;
	
	public AulaDtoDetalhada(Aula aula) {
		this.aulaId = aula.getAulaId();
		this.nome = aula.getNome();
		this.nivel = new ArrayList<>();
		this.nivel.addAll(aula.getNivel().stream().map(NivelDto::new).collect(Collectors.toList()));
//		this.turmas = new ArrayList<>();
//		this.turmas.addAll(aula.getTurmas().stream()
//				.map(TurmaDto::new)
//				.collect(Collectors.toList()));
	}
}
