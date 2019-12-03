package com.shakese.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Entity(name = "FuncionarioEntity")
@Table(name = "tbl_funcionario")
public class Funcionario implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -3535151955081280862L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long funcionarioId;
	
	@Embedded
	private Pessoa pessoa;
	
	@Column(name = "cargo")
	private String cargo;
	
	@Column(name = "salario")
	private double salario;
	
	@Column(name = "status")
	private boolean status = true;
	
	public Funcionario(Pessoa pessoa, String cargo, double salario) {
		this.pessoa = pessoa;
		this.cargo = cargo;
		this.salario = salario;
	}

}
