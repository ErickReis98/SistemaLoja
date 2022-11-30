package br.com.daotest.controller;

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

import br.com.daotest.model.Departamento;
import br.com.daotest.repository.DepartamentoRepository;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoRepository depRepo;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Departamento adicionarDepartamento(@RequestBody Departamento dep) {
		return depRepo.save(dep);
	}
	
	@GetMapping
	public List<Departamento> findAll(Departamento dep){
		return depRepo.findAll();
	}
	
	@DeleteMapping("/{depId}")
	public ResponseEntity<Void> deletarDepartamento(@PathVariable Long depId){
		if(!depRepo.existsById(depId)) {
			return ResponseEntity.noContent().build();
		}
		
		depRepo.deleteById(depId);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/{depId}")
	public ResponseEntity<Departamento> atualizarDep(@PathVariable Long depId, @RequestBody Departamento dep){
		if(!depRepo.existsById(depId)) {
			return ResponseEntity.noContent().build();
		}
		
		dep.setId(depId);
		dep = depRepo.save(dep);
		return ResponseEntity.ok(dep);
	}
}
