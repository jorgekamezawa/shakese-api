package com.shakese.controller.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Sexo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDtoDetalhado {

	private Long alunoId;
	private String nome;
	private LocalDate nascimento;
	private Sexo sexo;
	private String cpf;
	private EnderecoDto endereco;
	private List<TurmaDto> aulas;

	public AlunoDtoDetalhado(Aluno aluno) {
		this.alunoId = aluno.getAlunoId();
//		this.nome = aluno.getNome();
//		this.nascimento = aluno.getNascimento();
//		this.sexo = aluno.getSexo();
//		this.cpf = aluno.getCpf();
//		this.endereco = new EnderecoDto(aluno.getEndereco());
//		this.aulas = new ArrayList<TurmaDto>();
//		this.aulas.addAll(aluno.getAula().stream().map(TurmaDto::new).collect(Collectors.toList()));
	}
	
	
}
