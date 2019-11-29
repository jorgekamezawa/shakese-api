package com.shakese.controller.dto;

import com.shakese.modelo.Funcionario;
import com.shakese.modelo.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDtoDetalhado {
	
	private Long id;
	private Pessoa pessoa;
	private String cargo;
	private double salario;
	
	public FuncionarioDtoDetalhado (Funcionario funcionario) {
		this.id = funcionario.getFuncionarioId();
		this.pessoa = funcionario.getPessoa();
		this.cargo = funcionario.getCargo();
		this.salario = funcionario.getSalario();
	}

}
