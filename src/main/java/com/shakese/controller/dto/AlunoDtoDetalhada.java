package com.shakese.controller.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Endereco;
import com.shakese.modelo.Sexo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDtoDetalhada {
	
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private Sexo sexo;
	private String celular;
	private int falta;
	private LocalDate dataInicio;
	private Endereco endereco;
	private double desconto;
	private List<TurmaDto> turmas;
	
//	public AlunoDtoDetalhada(Aluno aluno) {
//		this.id = aluno.getAlunoId();
//		this.nome = aluno.getPessoa().getNome();
//		this.cpf = aluno.getPessoa().getCpf();
//		this.dataNascimento = aluno.getPessoa().getDataNascimento();
//		this.sexo = aluno.getPessoa().getSexo();
//		this.celular = aluno.getPessoa().getCelular();
//		this.falta = aluno.getPessoa().getFalta();
//		this.dataInicio = aluno.getPessoa().getDataInicio();
//		this.endereco = aluno.getPessoa().getEndereco();
//		this.desconto = aluno.getDesconto();
//		this.turmas = new ArrayList<>();
//		this.turmas.addAll(aluno.getTurmas().stream().map(TurmaDto::new)
//				.collect(Collectors.toList()));
//	}

}
