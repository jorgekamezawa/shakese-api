package com.shakese.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shakese.service.impl.AutenticacaoService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private AutenticacaoService autenticacaoService;

	//Configuraões de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Condifuraçoes de Autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/aluno").permitAll()
		.antMatchers(HttpMethod.GET, "/aluno/*").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();
	}
	
	//Configuraçoes de recursos estaticos(Ex: js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
	//Senha 123456: $2a$10$rsr5qerD9cVBI1zC1m6pF.QfQpzkVvUviGtjRtzpu/B8a3fKWCvX2
	
}
