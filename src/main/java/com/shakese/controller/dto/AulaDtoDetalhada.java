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
	private Nivel nivel;
	private List<TurmaDto> turmas;
	
	public AulaDtoDetalhada(Aula aula) {
		this.aulaId = aula.getAulaId();
		this.nome = aula.getNome();
		this.nivel = aula.getNivel();
		this.turmas = new ArrayList<>();
		this.turmas.addAll(aula.getTurmas().stream()
				.map(TurmaDto::new)
				.collect(Collectors.toList()));
	}
}
