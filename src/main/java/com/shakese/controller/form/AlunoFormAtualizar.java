package com.shakese.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Pessoa;
import com.shakese.modelo.Turma;
import com.shakese.service.IAlunoService;
import com.shakese.service.ITurmaService;

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
	private List<Long> idTurmas;

	public Aluno atualizar(Long id, IAlunoService alunoSevice,
			ITurmaService turmaService) {
		List<Turma> turmas = turmaService.findAll();

		List<Turma> turmasAluno = new ArrayList<Turma>();
		for (Long idTurma : idTurmas) {
			Optional<Turma> turma = turmaService.findById(idTurma);
			turmasAluno.add(turma.get());
		}

		int validar = 0;
		for (Turma turma : turmasAluno) {
			for (Turma turma2 : turmas) {
				if (turma.isStatus() && turma.getTurmaId() == turma2.getTurmaId()) {
					validar++;
					break;
				}
			}
		}

		if (validar >= turmasAluno.size()) {
			Optional<Aluno> aluno = alunoSevice.findById(id);

			aluno.get().setPessoa(pessoa);
			aluno.get().setDesconto(desconto);
			aluno.get().setTurmas(turmasAluno);

			return aluno.get();
		}
		return null;
	}
}
