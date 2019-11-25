package com.shakese.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakese.modelo.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
	//Turma findByNivelNome(String nome);

}
