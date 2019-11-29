package com.shakese.controller.form;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Funcionario;
import com.shakese.modelo.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioForm {

	@NotNull
	private Pessoa pessoa;
	
	@NotNull
	private String cargo;
	
	@NotNull
	private double salario;
	
	public Funcionario cadastrar() {
		return new Funcionario(pessoa, cargo, salario);
	}
	
}
