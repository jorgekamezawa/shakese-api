package com.shakese.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;

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

	@Column(name = "nome")
	private String nome;

	@Column(name = "nascimento")
	private LocalDate nascimento;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Column(name = "cpf")
	private String cpf;

	@ManyToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinTable(name = "tbl_aluno_aula", joinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "alunoId", foreignKey = @ForeignKey(name = "fk_aluno_aula_aluno")), inverseJoinColumns = @JoinColumn(name = "aula_id", referencedColumnName = "aulaId", foreignKey = @ForeignKey(name = "fk_aluno_aula_aula")))
	private List<Aula> aula;

	public Aluno(String nome, LocalDate nascimento, Sexo sexo, String cpf, Endereco endereco, List<Aula> aula) {
		this.nome = nome;
		this.nascimento = nascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.endereco = endereco;
		this.aula = aula;
	}
}
