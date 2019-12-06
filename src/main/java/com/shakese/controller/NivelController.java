package com.shakese.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shakese.controller.dto.NivelDto;
import com.shakese.controller.form.NivelForm;
import com.shakese.controller.form.NivelFormAtualizar;
import com.shakese.modelo.Nivel;
import com.shakese.service.INivelService;

@RestController
@RequestMapping("/nivel")
public class NivelController {

	@Autowired
	private INivelService nivelService;

	@GetMapping
	public List<NivelDto> listarNiveis() {
		List<Nivel> niveis = nivelService.findAll();

		return NivelDto.converter(niveis);
	}

	@GetMapping("/{id}")
	public ResponseEntity<NivelDto> listarDetalheNiveis(@PathVariable Long id) {
		Optional<Nivel> optional = nivelService.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(new NivelDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<NivelDto> cadastrarNivel(@RequestBody @Valid NivelForm nivelForm,
			UriComponentsBuilder uriBuilder) {
		Nivel nivel = nivelService.save(nivelForm.converter());

		URI uri = uriBuilder.path("/nivel/{id}").buildAndExpand(nivel.getNivelId()).toUri();
		return ResponseEntity.created(uri).body(new NivelDto(nivel));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<NivelDto> atualizarNivel(@PathVariable Long id, @RequestBody @Valid NivelFormAtualizar form) {
		Optional<Nivel> optional = nivelService.findById(id);
		
		if (optional.isPresent()) {
			Nivel nivel = form.atualizar(id, nivelService);
			return ResponseEntity.ok(new NivelDto(nivel));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarNivel(@PathVariable Long id) {
		Optional<Nivel> optional = nivelService.findById(id);
		
		if (optional.isPresent()) {
			nivelService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
