package com.shakese.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakese.modelo.Aluno;
import com.shakese.modelo.Professor;
import com.shakese.modelo.Turma;
import com.shakese.repository.TurmaRepository;
import com.shakese.service.IAlunoService;
import com.shakese.service.IProfessorService;
import com.shakese.service.ITurmaService;

@Service
public class TurmaServiceImpl implements ITurmaService{
	@Autowired
	private TurmaRepository turmaRepository;

	@Override
	public List<Turma> findAll() {
		return turmaRepository.findAll();
	}

	@Override
	public Optional<Turma> findById(Long id) {
		return turmaRepository.findById(id);
	}

	@Override
	public Turma save(Turma turma) {
		return turmaRepository.save(turma);
	}

	@Override
	public List<Turma> findByAulaNomeAndStatusTrue(String nome) {
		return turmaRepository.findByAulaNomeAndStatusTrue(nome);
	}
	
	@Override
	public void deletarTurmaAluno(IAlunoService alunoService,
			Optional<Turma> optional, Long id) {
		List<Aluno> alunos = alunoService.findAll();
		
			for (Aluno aluno : alunos) {
				Aluno a = new Aluno(aluno.getAlunoId(), aluno.getPessoa(), aluno.getDesconto());
				List<Turma> novasTurmas = new ArrayList<>();
				for (Turma turma : aluno.getTurmas()) {
					if(turma.getTurmaId() != id) {
						novasTurmas.add(turma);
					}
				}
				a.setTurmas(novasTurmas);
				alunoService.save(a);
			}
	}
	
	@Override
	public void deletarTurmaProfessor(IProfessorService professorService,
			Optional<Turma> optional, Long id) {
		List<Professor> professores = professorService.findAll();

		for (Professor professor : professores) {
			Professor p = new Professor(professor.getProfessorId(), professor.getPessoa(), professor.getSalario());
			List<Turma> novasTurmas = new ArrayList<>();
			for (Turma turma : professor.getTurmas()) {
				if(turma.getTurmaId() != id) {
					novasTurmas.add(turma);
				}
			}
				p.setTurmas(novasTurmas);
				professorService.save(p);
		}
	}

}
