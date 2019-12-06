package com.shakese.service;

import java.util.List;
import java.util.Optional;

import com.shakese.modelo.Nivel;

public interface INivelService {
	
	public List<Nivel> findAll();
	
	public Optional<Nivel> findById(Long id);
	
	public Nivel save(Nivel nivel);
	
	public void deleteById(Long id);

}
