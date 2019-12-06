package com.shakese.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Funcionario;
import com.shakese.modelo.Pessoa;
import com.shakese.service.IFuncionarioService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioFormAtualizar {
	
	@NotNull
	private Pessoa pessoa;
	
	@NotNull
	private String cargo;
	
	@NotNull
	private double salario;
	
	public Funcionario atualizar(Long id, IFuncionarioService funcionarioService) {
		Optional<Funcionario> funcionario = funcionarioService.findById(id);
		funcionario.get().setPessoa(pessoa);
		funcionario.get().setCargo(cargo);
		funcionario.get().setSalario(salario);
		
		return funcionario.get();
	}

}
