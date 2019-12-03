package com.shakese.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity(name = "TurmaEntity")
@Table(name = "tbl_turma")
public class Turma {

	@Transient
	private static final long serialVersionUID = -3535151955081280862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long turmaId;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Aula aula;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Nivel nivel;
	
	@Column(name = "preco")
	private double preco;
	
	//@OneToOne
	//private Calendario calendario;
	
	@Column(name = "status")
	private boolean status = true;
	
	public Turma(Aula aula, Nivel nivel, double preco) {
		this.aula = aula;
		this.nivel = nivel;
		this.preco = preco;
	}
}
