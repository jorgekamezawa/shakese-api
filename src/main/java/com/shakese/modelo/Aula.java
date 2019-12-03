package com.shakese.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
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
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "tbl_aula_nivel", joinColumns = {
	@JoinColumn(name = "aula_id", referencedColumnName = "aulaId")},
	inverseJoinColumns = {@JoinColumn(name = "nivel_id", referencedColumnName = "nivelId")})
	private List<Nivel> niveis;
	
	@Column(name = "status")
	private boolean status = true;
	
	public Aula (String nome, List<Nivel> niveis) {
		this.nome = nome;
		this.niveis = niveis;
	}
}
