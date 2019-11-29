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
public class AulaFormAtualizar {

	@NotNull
	private String nome;

	@NotNull
	private List<Long> idNivel;

	public Aula atualizar(Long id, AulaRepository aulaRepository, NivelRepository nivelRepository) {
		List<Nivel> niveis = new ArrayList<Nivel>();
		Optional<Aula> aula = aulaRepository.findById(id);
		for (Long nivelId : idNivel) {
			Optional<Nivel> nivel = nivelRepository.findById(nivelId);
			niveis.add(nivel.get());
		}
		aula.get().setNome(nome);
		aula.get().setNiveis(niveis);
		return aula.get();
	}
}