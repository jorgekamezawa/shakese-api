package com.shakese.modelo;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class Pessoa{

	private String nome;

	private String cpf;

	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	private String celular;
	
	private int falta = 0;
	
	private LocalDate dataInicio = LocalDate.now();

	private Endereco endereco;

	public Pessoa(String nome, String cpf, LocalDate dataNascimento, Sexo sexo, String celular, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.celular = celular;
		this.endereco = endereco;
	}

}
