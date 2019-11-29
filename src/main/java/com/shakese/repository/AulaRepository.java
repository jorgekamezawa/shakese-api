package com.shakese.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakese.modelo.Aula;

public interface AulaRepository extends JpaRepository<Aula, Long> {
	
	Optional<Aula> findByNome(String nome);
}
