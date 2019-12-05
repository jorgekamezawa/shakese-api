package com.shakese.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aula;
import com.shakese.modelo.Nivel;
import com.shakese.modelo.Turma;
import com.shakese.repository.AulaRepository;
import com.shakese.repository.NivelRepository;
import com.shakese.repository.TurmaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AulaFormAtualizar {

	@NotNull
	private String nome;
	@NotNull
	private List<Long> idNivel;

	public Aula atualizar(Long id, AulaRepository aulaRepository,
			NivelRepository nivelRepository,
			TurmaRepository turmaRepository) {
		Optional<Aula> aula = aulaRepository.findById(id);
		List<Turma> turmas = turmaRepository.findAll();

		List<Nivel> nivelTurmas = new ArrayList<>();
		for (Turma turma : turmas) {
			if (turma.isStatus() && turma.getAula().getNome().equals(nome)) {
				nivelTurmas.add(turma.getNivel());
			}
		}

		List<Nivel> niveis = new ArrayList<Nivel>();
		for (Long nivelId : idNivel) {
			Optional<Nivel> nivel = nivelRepository.findById(nivelId);
			niveis.add(nivel.get());
		}

		int validar = 0;
		for (Nivel nivelTurma : nivelTurmas) {
			for (Nivel nivel : niveis) {
				if (nivelTurma.getNome().equals(nivel.getNome()))
					validar++;
			}
		}

		if (validar < nivelTurmas.size())
			return null;
		else {
			aula.get().setNome(nome);
			aula.get().setNiveis(niveis);

			return aula.get();
		}
	}
}