package com.shakese.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shakese.modelo.Funcionario;
import com.shakese.repository.FuncionarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Funcionario> optional = funcionarioRepository.findByUsername(username);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new UsernameNotFoundException("Dados Invalidos!");
	}

}
