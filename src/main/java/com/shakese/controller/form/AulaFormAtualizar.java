package com.shakese.controller.form;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aula;
import com.shakese.modelo.Nivel;
import com.shakese.modelo.Turma;
import com.shakese.repository.AulaRepository;

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
	private Nivel nivel;
	
	private List<Turma> turmas;

	public Aula atualizar(Long id, AulaRepository aulaRepository) {
		Optional<Aula> aula = aulaRepository.findById(id);
		aula.get().setNome(nome);
		aula.get().setNivel(nivel);
		aula.get().setTurmas(turmas);
		return aula.get();
	}
}