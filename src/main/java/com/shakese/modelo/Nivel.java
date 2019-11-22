package com.shakese.modelo;

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
@Entity(name = "NivelEntity")
@Table(name = "tbl_nivel")
public class Nivel {
	
	@Transient
	private static final long serialVersionUID = -3535151955081280862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nivelId;
	
	@Column(name = "nome")
	private String nome;

	public Nivel(String nome) {
		this.nome = nome;
	}
	
	
	

}
