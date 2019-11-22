package com.shakese.controller.form;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Nivel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NivelForm {
	
	@NotNull
	private String nome;
	
	public Nivel converter() {
		return new Nivel(nome);
	}
}
