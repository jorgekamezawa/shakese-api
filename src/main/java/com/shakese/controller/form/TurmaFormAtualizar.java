package com.shakese.controller.form;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.shakese.modelo.Aula;
import com.shakese.modelo.Nivel;
import com.shakese.modelo.Turma;
import com.shakese.service.IAulaService;
import com.shakese.service.INivelService;
import com.shakese.service.ITurmaService;

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
	
//	@NotNull
//	private Calendario calendario;
	
	public Turma atualizar(Long id, ITurmaService turmaService, IAulaService aulaService,
			INivelService nivelService) {
		Optional<Aula> aula = aulaService.findById(aulaId);
		Optional<Nivel> nivel = nivelService.findById(nivelId);
		Optional<Turma> turma = turmaService.findById(id);
		//List<Calendario> calendarios = calendarioRepository.findAll();
		
		List<Turma> turmas = turmaService.findAll();
		for (Turma turma2 : turmas) {
			if(turma2.isStatus() && turma2.getTurmaId() != id 
					&& turma2.getAula().getNome().equals(aula.get().getNome()) 
					&& turma2.getNivel().getNome().equals(nivel.get().getNome())) {
				return null;
			}
		}
		
		for (Nivel niveis : aula.get().getNiveis()) {
			if (nivel.get().getNome() == niveis.getNome()) {
				turma.get().setNivel(nivel.get());
				turma.get().setAula(aula.get());
				turma.get().setPreco(this.preco);
				return turma.get();
			}
		}
		
//		for (Calendario calendarioTurmas : calendarios) {
//			for (Semana semana : calendarioTurmas.getSemana()) {
//				for (Semana semana2 : calendario.getSemana()) {
//					if(semana2.equals(semana) && calendarioTurmas.getInicio().equals(calendario.getInicio()))
//				}
//			}
//		}
		
		return null;
	}

}
