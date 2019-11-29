package com.shakese.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakese.modelo.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
