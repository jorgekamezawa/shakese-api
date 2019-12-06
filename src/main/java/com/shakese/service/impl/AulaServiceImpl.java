package com.shakese.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakese.modelo.Aula;
import com.shakese.repository.AulaRepository;
import com.shakese.service.IAulaService;

@Service
public class AulaServiceImpl implements IAulaService{
	
	@Autowired
	private AulaRepository aulaRepository;

	@Override
	public List<Aula> findAll() {
		return aulaRepository.findAll();
	}

	@Override
	public Optional<Aula> findById(Long id) {
		return aulaRepository.findById(id);
	}

	@Override
	public Aula save(Aula aula) {
		return aulaRepository.save(aula);
	}

}
