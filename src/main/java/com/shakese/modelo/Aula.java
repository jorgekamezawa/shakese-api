package com.shakese.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
public class Aula {

	@Transient
	private static final long serialVersionUID = -3535151955081280862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aulaId;

	@OneToOne
	@JoinColumn(unique=true)
	private Nivel nivel;

	@Column(name = "preco")
	private double preco;

	@ManyToMany(mappedBy = "aula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Aluno> aluno;

	public Aula(Nivel nivel, double preco) {
		this.nivel = nivel;
		this.preco = preco;
	}
}
