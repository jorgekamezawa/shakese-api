package com.shakese.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDto {
	
	private Long id;
	private String nome;
	private String cargo;
	private String celular;
	
	public FuncionarioDto (Funcionario funcionario) {
		this.id = funcionario.getFuncionarioId();
		this.nome = funcionario.getPessoa().getNome();
		this.cargo = funcionario.getCargo();
		this.celular = funcionario.getPessoa().getCelular();
	}
	
	public static List<FuncionarioDto> converter(List<Funcionario> funcionario){
		return funcionario.stream().map(FuncionarioDto::new).collect(Collectors.toList());
	}

}
