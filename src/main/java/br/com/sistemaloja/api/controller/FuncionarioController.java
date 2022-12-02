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

import br.com.sistemaloja.domain.controller.model.Funcionario;
import br.com.sistemaloja.domain.controller.repository.FuncionarioRepository;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcRepo;
	
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario adicionarDepartamento(@RequestBody Funcionario func) {
		return funcRepo.save(func);
	}

	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll() {
		List<Funcionario> func = funcRepo.findAll();
		return ResponseEntity.ok().body(func);
	}
	
	
	@GetMapping(value = "findByDep")
	public ResponseEntity<List<Object>> findByDep(@RequestParam(name = "departamento_id") Long departamento_id) {
		List<Object> func = funcRepo.findByDepContaning(departamento_id);
		return new ResponseEntity<List<Object>>(func, HttpStatus.OK);
	}
	
	@GetMapping(value = "findByNome")
	@ResponseBody
	public ResponseEntity<List<Object>> findByNome(@RequestParam (name = "nome")String nome){
		List<Object> func = funcRepo.findByNomeContaining(nome);
		return new ResponseEntity<List<Object>>(func, HttpStatus.OK);
	}
	 



	@DeleteMapping({"/{vendId}"})
	public ResponseEntity<Void> deletarVend(@PathVariable Long vendId) {
		if (!funcRepo.existsById(vendId)) {
			return ResponseEntity.noContent().build();
		}
		funcRepo.deleteById(vendId);
		return ResponseEntity.noContent().build();

	}

	@PutMapping("{vendId}")
	public ResponseEntity<Funcionario> atualizarVend(@PathVariable Long vendId, @RequestBody Funcionario funcionario) {
		if (!funcRepo.existsById(vendId)) {
			return ResponseEntity.noContent().build();
		}
		funcionario.setId(vendId);
		funcionario = funcRepo.save(funcionario);
		return ResponseEntity.ok(funcionario);
	}

}
