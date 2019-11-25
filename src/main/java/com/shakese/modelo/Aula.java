package com.shakese.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "AulaEntity")
@Table(name = "tbl_aula")
public class Aula implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -3535151955081280862L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aulaId;
	
	@Column(name = "nome")
	private String nome;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Nivel nivel;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Turma> turmas;
	
	public Aula (String nome, Nivel nivel, List<Turma> turma) {
		this.nome = nome;
		this.nivel = nivel;
		this.turmas = turma;
	}
	
	public Aula (String nome, Nivel nivel) {
		this.nome = nome;
		this.nivel = nivel;
	}

}
