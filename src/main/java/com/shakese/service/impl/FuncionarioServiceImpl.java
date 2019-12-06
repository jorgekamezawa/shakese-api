package com.shakese.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakese.modelo.Funcionario;
import com.shakese.repository.FuncionarioRepository;
import com.shakese.service.IFuncionarioService;

@Service
public class FuncionarioServiceImpl implements IFuncionarioService{
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}

	@Override
	public Optional<Funcionario> findById(Long id) {
		return funcionarioRepository.findById(id);
	}

	@Override
	public Funcionario save(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

}
