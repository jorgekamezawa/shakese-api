package com.shakese.controller.form;

import com.shakese.modelo.Nivel;
import com.shakese.repository.NivelRepository;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarNivelForm {

	@NotNull
	private String nome;
	
	public Nivel atualizar(Long id, NivelRepository nivelRepository) {
		Nivel nivel = nivelRepository.getOne(id);
		nivel.setNome(nome);
		return nivel;
	}
	
}
