package com.shakese.controller.form;

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
public class TurmaFormAtualizar {
	
	@NotNull
	private Long aulaId;
	
	@NotNull
	private Long nivelId;
	
	@NotNull
	private double preco;
	
	public Turma atualizar(Long id, TurmaRepository turmaRepository, AulaRepository aulaRepository,
			NivelRepository nivelRepository) {
		Optional<Aula> aula = aulaRepository.findById(aulaId);
		Optional<Nivel> nivel = nivelRepository.findById(nivelId);
		Optional<Turma> turma = turmaRepository.findById(id);
		turma.get().setAula(aula.get());
		turma.get().setPreco(this.preco);
		
		for (Nivel niveis : aula.get().getNiveis()) {
			if (nivel.get().getNome() == niveis.getNome()) {
				turma.get().setNivel(nivel.get());
				return turma.get();
			}
		}
		
		return null;
	}

}
