package com.shakese.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.shakese.controller.dto.AlunoDtoDetalhado;
import com.shakese.modelo.Aluno;

public interface IAlunoService {
	
	public List<Aluno> findAll();
	
	public Optional<Aluno> findById(Long id);
	
	public Aluno save(Aluno aluno);
	
	public ResponseEntity<AlunoDtoDetalhado> validacao(Long id);

}
