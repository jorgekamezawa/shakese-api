package com.shakese.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakese.modelo.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
	List<Turma> findByAulaNomeAndStatusTrue(String nome);

}
