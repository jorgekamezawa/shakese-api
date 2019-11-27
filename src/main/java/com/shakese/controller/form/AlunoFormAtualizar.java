package com.shakese.controller.form;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Pessoa;
import com.shakese.modelo.Turma;
import com.shakese.repository.AlunoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoFormAtualizar {

	@NotNull
	private Pessoa pessoa;
	@NotNull
	private double desconto;
	@NotNull
	private List<Turma> turmas;
	
	public Aluno atualizar(Long id, AlunoRepository alunoRepository) {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		aluno.get().setPessoa(pessoa);
		aluno.get().setDesconto(desconto);
		aluno.get().setTurmas(turmas);
		return aluno.get();
	}
}
