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

	public Aula cadastrar(AulaRepository aulaRepository,
			NivelRepository nivelRepository) {
		List<Aula> aulas = aulaRepository.findAll();

		for (Aula nomeAula : aulas) {
			if (nome.equals(nomeAula.getNome())) {
				if (nomeAula.isStatus())
					return null;
				else {
					nomeAula.setStatus(true);

					for (Long id : idNivel) {
						Optional<Nivel> nivel = nivelRepository.findById(id);
						nomeAula.getNiveis().add(nivel.get());
					}
					return nomeAula;
				}
			}
		}

		List<Nivel> niveis = new ArrayList<>();
		for (Long id : idNivel) {
			Optional<Nivel> nivel = nivelRepository.findById(id);
			niveis.add(nivel.get());
		}
		return new Aula(nome, niveis);
	}
}
