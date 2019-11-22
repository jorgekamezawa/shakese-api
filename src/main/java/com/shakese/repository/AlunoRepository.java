package com.shakese.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakese.modelo.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	//List<Aluno> findByAulaNivel(Niveis nivel);

}
