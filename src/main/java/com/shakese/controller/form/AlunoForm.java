package com.shakese.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Pessoa;
import com.shakese.modelo.Turma;
import com.shakese.service.ITurmaService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoForm {

	@NotNull
	private Pessoa pessoa;
	@NotNull
	private double desconto;
	@NotNull
	private List<Long> idTurmas;

	public Aluno cadastrar(ITurmaService turmaService) {
		List<Turma> turmasAluno = new ArrayList<Turma>();

		for (Long id : idTurmas) {
			Optional<Turma> turma = turmaService.findById(id);
			turmasAluno.add(turma.get());
		}
		return new Aluno(pessoa, desconto, turmasAluno);
	}
}
