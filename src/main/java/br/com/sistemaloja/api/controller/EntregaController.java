package br.com.sistemaloja.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaloja.domain.model.Entrega;
import br.com.sistemaloja.domain.repository.EntregaRepository;
import br.com.sistemaloja.domain.service.EntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepo;
	private EntregaService entregaServ;

	public EntregaController(EntregaRepository entregaRepo, EntregaService entregaServ) {
		super();
		this.entregaRepo = entregaRepo;
		this.entregaServ = entregaServ;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitarEntrega(@Valid @RequestBody Entrega entrega) {
		return entregaServ.solicitar(entrega);
	}

	@GetMapping
	public ResponseEntity<List<Entrega>> listar() {
		return ResponseEntity.ok().body(entregaServ.listar());
	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<Entrega> listarById(@PathVariable Long entregaId) {
		return entregaRepo.findById(entregaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
}
