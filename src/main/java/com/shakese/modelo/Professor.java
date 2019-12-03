package com.shakese.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ProfessorEntity")
@Table(name = "tbl_professor")
public class Professor implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -3535151955081280862L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long professorId;
	
	@Embedded
	private Pessoa pessoa;
	
	@Column(name = "salario")
	private double salario;
	
	@Column(name = "status")
	private boolean status = true;
	
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Turma> turmas;
	
	public Professor (Pessoa pessoa, double salario, List<Turma> turmas) {
		this.pessoa = pessoa;
		this.salario = salario;
		this.turmas = turmas;
	}

}
