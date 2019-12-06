package com.shakese.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakese.modelo.Professor;
import com.shakese.repository.ProfessorRepository;
import com.shakese.service.IProfessorService;

@Service
public class ProfessorServiceImpl implements IProfessorService{
	@Autowired
	private ProfessorRepository professorRepository;

	@Override
	public List<Professor> findAll() {
		return professorRepository.findAll();
	}

	@Override
	public Optional<Professor> findById(Long id) {
		return professorRepository.findById(id);
	}

	@Override
	public Professor save(Professor professor) {
		return professorRepository.save(professor);
	}

}
