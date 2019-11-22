package com.shakese.controller.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Aula;
import com.shakese.modelo.Endereco;
import com.shakese.modelo.Sexo;
import com.shakese.repository.AulaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroAlunoForm {

	@NotNull
	@Length(max = 30)
	private String nome;

	@NotNull
	private LocalDate nascimento;

	@NotNull
	private Sexo sexo;

	@NotNull
	@Length(max = 11)
	private String cpf;

	@NotNull
	private Endereco endereco;

	@NotNull
	private List<String> nivelAulas;

	public Aluno converter(AulaRepository aulaRepository) {
		List<Aula> aulas = new ArrayList<>();
		for (int i = 0; i < nivelAulas.size(); i++) {
			String nivel = nivelAulas.get(i);
			Aula aula = aulaRepository.findByNivelNome(nivel);
			aulas.add(aula);
		}
		return new Aluno(nome, nascimento, sexo, cpf, endereco, aulas);
	}

}
