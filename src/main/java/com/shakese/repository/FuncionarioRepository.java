package com.shakese.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakese.modelo.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	Optional<Funcionario> findByUsername(String username);

}
