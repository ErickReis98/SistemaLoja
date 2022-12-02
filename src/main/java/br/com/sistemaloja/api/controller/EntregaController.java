package br.com.sistemaloja.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaloja.domain.controller.model.Entrega;
import br.com.sistemaloja.domain.controller.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService soliServ;
	
	
	public EntregaController(SolicitacaoEntregaService soliServ) {
		super();
		this.soliServ = soliServ;
	}


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega criarEntrega(@RequestBody Entrega entrega) {
		return soliServ.solicitar(entrega);
	}
}