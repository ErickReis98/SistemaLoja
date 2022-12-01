package br.com.sistemaloja.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaloja.domain.controller.model.Cliente;
import br.com.sistemaloja.domain.controller.repository.ClienteRepository;
import br.com.sistemaloja.domain.controller.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
 
		@Autowired
		private ClienteRepository cRepository;
		private ClienteService cService;
		
		public ClienteController(ClienteRepository clientes, ClienteService service) {
			super();
			this.cRepository = clientes;
			this.cService = service;
		}

		@GetMapping
		public ResponseEntity<List<Cliente>> Listar() { 
			return ResponseEntity.ok().body(cService.listarTodos()); 
		}
		
		@PostMapping
		@ResponseStatus(value = HttpStatus.CREATED)
		public Cliente adicionar( @RequestBody Cliente cliente) {
			return cService.salvar(cliente);
		}
		
		@GetMapping("/{clienteId}")
		public ResponseEntity<Cliente> findById(@PathVariable Long clienteId) {
			return cRepository.findById(clienteId)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
			
		}
		
		@PutMapping("/{clienteId}")
		public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
			if (!cRepository.existsById(clienteId)) {
				return ResponseEntity.notFound().build();
			}
			
			cliente.setId(clienteId);
			cliente = cService.salvar(cliente);
			return ResponseEntity.ok(cliente);
		}
		
		@Transactional
		@DeleteMapping("/{clienteId}")
		public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {
			if (!cRepository.existsById(clienteId)) {
				return ResponseEntity.notFound().build();
			}
			
			cService.excluir(clienteId);
			return ResponseEntity.noContent().build();
		}

	}

