package com.shakese.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakese.modelo.Nivel;
import com.shakese.repository.NivelRepository;
import com.shakese.service.INivelService;

@Service
public class NivelServiceImpl implements INivelService{
	@Autowired
	private NivelRepository nivelRepository;
	
	@Override
	public List<Nivel> findAll() {
		return nivelRepository.findAll();
	}

	@Override
	public Optional<Nivel> findById(Long id) {
		return nivelRepository.findById(id);
	}

	@Override
	public Nivel save(Nivel nivel) {
		return nivelRepository.save(nivel);
	}

	@Override
	public void deleteById(Long id) {
		nivelRepository.deleteById(id);
		return;
	}

}
