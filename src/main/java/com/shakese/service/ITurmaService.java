package com.shakese.service;

import java.util.List;
import java.util.Optional;

import com.shakese.modelo.Turma;

public interface ITurmaService {

	public List<Turma> findAll();

	public Optional<Turma> findById(Long id);

	public Turma save(Turma turma);

	public List<Turma> findByAulaNomeAndStatusTrue(String nome);

	public void deletarTurmaAluno(IAlunoService alunoService, Optional<Turma> optional, Long id);

	public void deletarTurmaProfessor(IProfessorService professorService, Optional<Turma> optional, Long id);
}
