package com.shakese.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Aula aula;
	
	@Column(name = "preco")
	private double preco;
	
	//@OneToOne
	//private Calendario calendario;
	
	@ManyToOne
	private Professor professor;
	
	@ManyToMany(mappedBy = "turma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Aluno> alunos;

	public Turma(Aula aula, double preco, Professor professor, List<Aluno> aluno) {
		this.preco = preco;
		this.aula = aula;
		this.professor = professor;
		this.alunos = aluno;
	}
	
	public Turma(Aula aula, double preco) {
		this.preco = preco;
		this.aula = aula;
	}
}
