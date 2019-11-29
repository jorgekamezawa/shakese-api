package com.shakese.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakese.modelo.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
