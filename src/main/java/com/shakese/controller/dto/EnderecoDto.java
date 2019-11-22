package com.shakese.controller.dto;

import com.shakese.modelo.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {

	private String rua;
	private String bairro;
	private int numero;
	private String cep;

	public EnderecoDto(Endereco endereco) {
		this.rua = endereco.getRua();
		this.bairro = endereco.getBairro();
		this.numero = endereco.getNumero();
		this.cep = endereco.getCep();
	}
}
