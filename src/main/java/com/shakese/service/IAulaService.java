package com.shakese.service;

import java.util.List;
import java.util.Optional;

import com.shakese.modelo.Aula;

public interface IAulaService {
	
	public List<Aula> findAll();
	
	public Optional<Aula> findById(Long id);
	
	public Aula save(Aula aula);

}
