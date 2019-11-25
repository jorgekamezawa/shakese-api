package com.shakese.modelo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PessoaEntity")
@Table(name = "tbl_pessoa")
public class Pessoa implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -3535151955081280862L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pessoaId;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	private String cpf;

	@Column(name = "dataNascimento")
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Column(name = "celular")
	private String celular;
	
	@Column(name = "falta")
	private int falta = 0;
	
	@Column(name = "dataInicio")
	private LocalDate dataInicio;

	@ManyToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	public Pessoa(String nome, String cpf, LocalDate dataNascimento, Sexo sexo, String celular, int falta, LocalDate dataInicio, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.celular = celular;
		this.falta = falta;
		this.dataInicio = dataInicio;
		this.endereco = endereco;
	}

}
