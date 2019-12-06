//package com.shakese.modelo;
//
//import java.io.Serializable;
//import java.time.LocalTime;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name = "CalendarioEntity")
//@Table(name = "tbl_calendario")
//public class Calendario implements Serializable {
//	
//	@Transient
//	private static final long serialVersionUID = -3535151955081280862L;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long calendarioId;
//	
//	@Column(name = "semana")
//	private List<Semana> semana;
//	
//	@Column(name = "inicio")
//	private LocalTime inicio;
//	
//	@Column(name = "termino")
//	private LocalTime termino;
//	
//	public Calendario(List<Semana> semana, LocalTime inicio, LocalTime termino) {
//		this.semana = semana;
//		this.inicio = inicio;
//		this.termino = termino;
//	}
//
//}
