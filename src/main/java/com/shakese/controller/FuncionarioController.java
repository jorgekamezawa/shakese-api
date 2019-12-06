package com.shakese.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shakese.controller.dto.FuncionarioDto;
import com.shakese.controller.dto.FuncionarioDtoDetalhado;
import com.shakese.controller.form.FuncionarioForm;
import com.shakese.controller.form.FuncionarioFormAtualizar;
import com.shakese.modelo.Funcionario;
import com.shakese.service.IEnderecoService;
import com.shakese.service.IFuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private IFuncionarioService funcionarioService;
	@Autowired
	private IEnderecoService enderecoService;

	@GetMapping
	public List<FuncionarioDto> listarFuncionarios() {
		List<Funcionario> funcionarios = funcionarioService.findAll();

		return FuncionarioDto
				.converter(funcionarios.stream().filter(Funcionario::isStatus).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDtoDetalhado> listarFuncionario(@PathVariable Long id) {
		Optional<Funcionario> optional = funcionarioService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			return ResponseEntity.ok(new FuncionarioDtoDetalhado(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<FuncionarioDto> cadastrarFuncionario(@RequestBody @Valid FuncionarioForm form,
			UriComponentsBuilder uriBuilder) {
		funcionarioService.save(form.cadastrar());
		enderecoService.save(form.getPessoa().getEndereco());

		URI uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(form.cadastrar().getFuncionarioId()).toUri();
		return ResponseEntity.created(uri).body(new FuncionarioDto(form.cadastrar()));

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FuncionarioDto> atualizarFuncionario(@PathVariable Long id,
			@RequestBody @Valid FuncionarioFormAtualizar form) {
		Optional<Funcionario> optional = funcionarioService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			Funcionario funcionario = form.atualizar(id, funcionarioService);
			return ResponseEntity.ok(new FuncionarioDto(funcionario));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarFuncionario(@PathVariable Long id) {
		Optional<Funcionario> optional = funcionarioService.findById(id);

		if (optional.isPresent() && optional.get().isStatus()) {
			optional.get().setStatus(false);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
