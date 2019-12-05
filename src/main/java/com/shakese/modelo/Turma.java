package com.shakese.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.shakese.repository.AlunoRepository;
import com.shakese.repository.ProfessorRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TurmaEntity")
@Table(name = "tbl_turma")
public class Turma {

	@Transient
	private static final long serialVersionUID = -3535151955081280862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long turmaId;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Aula aula;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Nivel nivel;
	
	@Column(name = "preco")
	private double preco;
	
	//@OneToOne
	//private Calendario calendario;
	
	@Column(name = "status")
	private boolean status = true;
	
	public Turma(Aula aula, Nivel nivel, double preco) {
		this.aula = aula;
		this.nivel = nivel;
		this.preco = preco;
	}
	
	public void deletarTurmaAluno(AlunoRepository alunoRepository,
			Optional<Turma> optional, Long id) {
		List<Aluno> alunos = alunoRepository.findAll();
		
			for (Aluno aluno : alunos) {
				Aluno a = new Aluno(aluno.getAlunoId(), aluno.getPessoa(), aluno.getDesconto());
				List<Turma> novasTurmas = new ArrayList<>();
				for (Turma turma : aluno.getTurmas()) {
					if(turma.getTurmaId() != id) {
						novasTurmas.add(turma);
					}
				}
				a.setTurmas(novasTurmas);
				alunoRepository.save(a);
			}
	}
	
	public void deletarTurmaProfessor(ProfessorRepository professorRepository,
			Optional<Turma> optional, Long id) {
		List<Professor> professores = professorRepository.findAll();

		for (Professor professor : professores) {
			Professor p = new Professor(professor.getProfessorId(), professor.getPessoa(), professor.getSalario());
			List<Turma> novasTurmas = new ArrayList<>();
			for (Turma turma : professor.getTurmas()) {
				if(turma.getTurmaId() != id) {
					novasTurmas.add(turma);
				}
			}
				p.setTurmas(novasTurmas);
				professorRepository.save(p);
		}
	}
}
