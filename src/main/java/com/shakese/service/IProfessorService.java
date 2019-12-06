package com.shakese.service;

import java.util.List;
import java.util.Optional;

import com.shakese.modelo.Professor;

public interface IProfessorService {
	
	public List<Professor> findAll();
	
	public Optional<Professor> findById(Long id);
	
	public Professor save(Professor professor);

}
