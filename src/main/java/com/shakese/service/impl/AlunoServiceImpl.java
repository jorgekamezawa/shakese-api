package com.shakese.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shakese.controller.dto.AlunoDtoDetalhado;
import com.shakese.modelo.Aluno;
import com.shakese.repository.AlunoRepository;
import com.shakese.service.IAlunoService;

@Service
public class AlunoServiceImpl implements IAlunoService{
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Override
	public List<Aluno> findAll(){
		return alunoRepository.findAll();
	}
	
	@Override
	public Optional<Aluno> findById(Long id){
		return alunoRepository.findById(id);
	}
	
	@Override
	public Aluno save(Aluno aluno){
		return alunoRepository.save(aluno);
	}

	@Override
	public ResponseEntity<AlunoDtoDetalhado> validacao(Long id) {
		Optional<Aluno> optional = alunoRepository.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			return ResponseEntity.ok(new AlunoDtoDetalhado(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

}
