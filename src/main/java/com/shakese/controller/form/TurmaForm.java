package com.shakese.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aula;
import com.shakese.modelo.Nivel;
import com.shakese.modelo.Turma;
import com.shakese.repository.AulaRepository;
import com.shakese.repository.NivelRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaForm {
	
	@NotNull
	private Long aulaId;
	
	@NotNull
	private Long nivelId;
	
	@NotNull
	private double preco;
	
	public Turma converter(AulaRepository aulaRepository, NivelRepository nivelRepository) {
		Optional<Aula> aula = aulaRepository.findById(aulaId);
		
		Optional<Nivel> nivel = nivelRepository.findById(nivelId);
		
		return new Turma(aula.get(), nivel.get(), preco);
	}

}
