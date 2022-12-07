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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaloja.domain.model.Funcionario;
import br.com.sistemaloja.domain.repository.FuncionarioRepository;
import br.com.sistemaloja.domain.service.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcRepo;

	private FuncionarioService funcServ;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Funcionario> adicionarFuncionario(@RequestBody Funcionario func) {
		return ResponseEntity.ok().body(funcServ.adicionar(func));
	}

	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll() {
		return ResponseEntity.ok().body(funcServ.listarTodos());
	}

	@PutMapping(value = "/{funcId}")
	public ResponseEntity<Funcionario> alterarDepartamento(@RequestBody Funcionario func, @PathVariable Long funcId) {
		if (!funcRepo.existsById(funcId)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(funcServ.alterar(func, funcId));
	}

	@DeleteMapping("/{funcId}")
	public ResponseEntity<Void> deletarDepartamento(@PathVariable Long funcId) {
		if (!funcRepo.existsById(funcId)) {
			return ResponseEntity.noContent().build();
		}
		funcServ.deletar(funcId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "findByDep")
	public ResponseEntity<List<Object>> findByDep(@RequestParam(name = "id_departamento") Long id_departamento) {
		return new ResponseEntity<List<Object>>(funcServ.findByDepartamento(id_departamento), HttpStatus.OK);
	}

	@GetMapping(value = "findByNome")
	@ResponseBody
	public ResponseEntity<List<Object>> findByNome(@RequestParam(name = "nome") String nome) {
		return new ResponseEntity<List<Object>>(funcServ.findByNome(nome), HttpStatus.OK);
	}
}
