package com.shakese.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakese.modelo.Aula;

public interface AulaRepository extends JpaRepository<Aula, Long> {
	
	Aula findByNivelNome(String nome);

}
