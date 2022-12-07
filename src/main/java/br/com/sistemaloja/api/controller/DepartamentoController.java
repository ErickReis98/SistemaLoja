package br.com.sistemaloja.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaloja.domain.model.Departamento;
import br.com.sistemaloja.domain.repository.DepartamentoRepository;
import br.com.sistemaloja.domain.service.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoRepository depRepo;

	private DepartamentoService depService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Departamento> adicionarDepartamento(@RequestBody Departamento dep) {
		return ResponseEntity.ok().body(depService.adicionar(dep));
	}

	@GetMapping
	public ResponseEntity<List<Departamento>> findAll(Departamento dep) {
		return ResponseEntity.ok().body(depService.listarTodos());
	}

	@PutMapping(value = "/{depId}")
	public ResponseEntity<Departamento> alterarDepartamento(@RequestBody Departamento dep, @PathVariable Long depId) {
		if (!depRepo.existsById(depId)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(depService.alterar(dep, depId));
	}

	@DeleteMapping("/{depId}")
	public ResponseEntity<Void> deletarDepartamento(@PathVariable Long depId) {
		if (!depRepo.existsById(depId)) {
			return ResponseEntity.noContent().build();
		}
		depService.deletar(depId);
		return ResponseEntity.noContent().build();
	}
}
