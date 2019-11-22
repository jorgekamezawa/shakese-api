package com.shakese.controller.form;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aula;
import com.shakese.modelo.Nivel;
import com.shakese.repository.NivelRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AulaForm {

	@NotNull
	private String nome;

	@NotNull
	private double preco;

	public Aula converter(NivelRepository nivelRepository) {
		Nivel nivel = nivelRepository.findByNome(nome);
		
		return new Aula(nivel, preco);
	}

}