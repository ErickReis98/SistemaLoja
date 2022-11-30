package br.com.sistemaloja.controller;

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

import br.com.sistemaloja.model.Vendedor;
import br.com.sistemaloja.repository.VendedorRepository;

@RestController
@RequestMapping(value = "/vendedor")
public class VendedorController {

	@Autowired
	private VendedorRepository vendRepo;
	
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Vendedor adicionarDepartamento(@RequestBody Vendedor vend) {
		return vendRepo.save(vend);
	}

	@GetMapping
	public ResponseEntity<List<Vendedor>> findAll() {
		List<Vendedor> vend = vendRepo.findAll();
		return ResponseEntity.ok().body(vend);
	}
	
	
	@GetMapping(value = "findByDep")
	public ResponseEntity<List<Object>> findByDep(@RequestParam(name = "id_departamento") Long id_departamento) {
		List<Object> vend = vendRepo.findByDepContaning(id_departamento);
		return new ResponseEntity<List<Object>>(vend, HttpStatus.OK);
	}
	
	@GetMapping(value = "findByNome")
	@ResponseBody
	public ResponseEntity<List<Object>> findByNome(@RequestParam (name = "nome")String nome){
		List<Object> vendedor = vendRepo.findByNomeContaining(nome);
		return new ResponseEntity<List<Object>>(vendedor, HttpStatus.OK);
	}
	 



	@DeleteMapping({"/{vendId}"})
	public ResponseEntity<Void> deletarVend(@PathVariable Long vendId) {
		if (!vendRepo.existsById(vendId)) {
			return ResponseEntity.noContent().build();
		}
		vendRepo.deleteById(vendId);
		return ResponseEntity.noContent().build();

	}

	@PutMapping("{vendId}")
	public ResponseEntity<Vendedor> atualizarVend(@PathVariable Long vendId, @RequestBody Vendedor vendedor) {
		if (!vendRepo.existsById(vendId)) {
			return ResponseEntity.noContent().build();
		}
		vendedor.setId(vendId);
		vendedor = vendRepo.save(vendedor);
		return ResponseEntity.ok(vendedor);
	}

}
