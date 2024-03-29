package com.shakese.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EnderecoEntity")
@Table(name = "tbl_endereco")
public class Endereco implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -3535151955081280862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long enderecoId;

	@Column(name = "rua")
	private String rua;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "numero")
	private int numero;

	@Column(name = "cep")
	private String cep;

	public Endereco(String rua, String bairro, int numero, String cep) {
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
	}
}
