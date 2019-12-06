package com.shakese.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakese.modelo.Endereco;
import com.shakese.repository.EnderecoRepository;
import com.shakese.service.IEnderecoService;

@Service
public class EnderecoServiceImpl implements IEnderecoService{
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

}
