package com.shakese.controller.form;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aula;
import com.shakese.repository.AulaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarAulaForm {
	
	@NotNull
	private double preco;
	
	public Aula atualizar(Long id, AulaRepository aulaRepository) {
		Aula aula = aulaRepository.getOne(id);
		aula.setPreco(this.preco);
		return aula;
	}

}
