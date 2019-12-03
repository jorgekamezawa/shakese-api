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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "AlunoEntity")
@Table(name = "tbl_aluno")
public class Aluno implements Serializable { // pesquisar sobre serealizable

	@Transient
	private static final long serialVersionUID = -3535151955081280862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long alunoId;
	
	@Embedded
	private Pessoa pessoa;

	@Column(name = "desconto")
	private double desconto;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "tbl_aluno_turma", joinColumns = {
	@JoinColumn(name = "aluno_id", referencedColumnName = "alunoId")},
	inverseJoinColumns = {@JoinColumn(name = "turma_id", referencedColumnName = "turmaId")})
	private List<Turma> turmas;
	
	@Column(name = "status")
	private boolean status = true;

	public Aluno(Pessoa pessoa, double desconto, List<Turma> turmas) {
		this.pessoa = pessoa;
		this.desconto = desconto;
		this.turmas = turmas;
	}
}
