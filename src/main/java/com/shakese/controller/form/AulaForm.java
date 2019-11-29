package com.shakese.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aula;
import com.shakese.modelo.Nivel;
import com.shakese.repository.AulaRepository;
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
	private List<Long> idNivel;

	public Aula converter(AulaRepository aulaRepository, NivelRepository nivelRepository) {
		List<Nivel> niveis = new ArrayList<Nivel>();
		for (Long id : idNivel) {
			Optional<Nivel> nivel = nivelRepository.findById(id);
			niveis.add(nivel.get());
		}
		Optional<Aula> optional = aulaRepository.findByNome(nome);
		if(optional.isPresent()) {
			optional.get().getNiveis().addAll(niveis);
			return optional.get();
		}
		
		return new Aula(nome, niveis);
	}
	
}
