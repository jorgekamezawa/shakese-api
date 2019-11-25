package com.shakese.controller.form;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aula;
import com.shakese.modelo.Turma;
import com.shakese.repository.AulaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaForm {
	
	@NotNull
	private Aula aulaTurma;
	
	@NotNull
	private double preco;
	
	public Turma converter(AulaRepository aulaRepository) {
		String aulaNome = aulaTurma.getNome();
		String aulaNivel = aulaTurma.getNivel().getNome();
		
		Aula aula = aulaRepository.findByNomeAndNivelNome(aulaNome, aulaNivel);
		
		return new Turma(aula, preco);
	}

}
