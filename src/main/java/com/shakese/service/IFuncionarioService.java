package com.shakese.service;

import java.util.List;
import java.util.Optional;

import com.shakese.modelo.Funcionario;

public interface IFuncionarioService {
	
	public List<Funcionario> findAll();
	
	public Optional<Funcionario> findById(Long id);
	
	public Funcionario save(Funcionario funcionario);

}
