package com.shakese.controller.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Endereco;
import com.shakese.repository.AlunoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoFormAtualizar {

	@NotNull
	@Length(max = 30)
	private String nome;

	@NotNull
	private Endereco endereco;

//	public Aluno atualizar(Long id, AlunoRepository alunoRepository) {
//		Aluno aluno = alunoRepository.getOne(id);
//		aluno.setNome(nome);
//		aluno.setEndereco(endereco);
//		return aluno;
//	}
}
