package com.shakese.controller.form;

import java.util.Optional;

import com.shakese.modelo.Nivel;
import com.shakese.service.INivelService;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NivelFormAtualizar {

	@NotNull
	private String nome;
	
	public Nivel atualizar(Long id, INivelService nivelService) {
		Optional<Nivel> nivel = nivelService.findById(id);
		nivel.get().setNome(nome);
		return nivel.get();
	}
	
}
