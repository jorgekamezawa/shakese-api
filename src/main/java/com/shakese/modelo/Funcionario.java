package com.shakese.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "FuncionarioEntity")
@Table(name = "tbl_funcionario")
public class Funcionario implements UserDetails {
	
	@Transient
	private static final long serialVersionUID = -3535151955081280862L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long funcionarioId;
	
	@Embedded
	private Pessoa pessoa;
	
	@Column(name = "cargo")
	private String cargo;
	
	@Column(name = "salario")
	private double salario;
	
	@Column(name = "usuario")
	private String usuario;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "status")
	private boolean status = true;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();
	
	public Funcionario(Pessoa pessoa, String cargo, double salario) {
		this.pessoa = pessoa;
		this.cargo = cargo;
		this.salario = salario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
