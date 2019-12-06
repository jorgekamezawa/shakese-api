package com.shakese.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aula;
import com.shakese.modelo.Nivel;
import com.shakese.modelo.Turma;
import com.shakese.service.IAulaService;
import com.shakese.service.INivelService;

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
	
	public Turma converter(IAulaService aulaService, INivelService nivelService) {
		Optional<Aula> aula = aulaService.findById(aulaId);
		
		Optional<Nivel> nivel = nivelService.findById(nivelId);
		
		for (Nivel niveis : aula.get().getNiveis()) {
			if (nivel.get().getNome() == niveis.getNome()) {
				return new Turma(aula.get(), nivel.get(), preco);
			}
		}
		
		return new Turma(aula.get(), null, preco);
	}

}
