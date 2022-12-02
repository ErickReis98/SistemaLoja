package br.com.sistemaloja.domain.controller.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaloja.domain.controller.model.Entrega;
import br.com.sistemaloja.domain.controller.model.StatusEntrega;
import br.com.sistemaloja.domain.controller.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {

	private EntregaRepository entregaRepo;

	public SolicitacaoEntregaService(EntregaRepository entregaRepo) {
		super();
		this.entregaRepo = entregaRepo;
	}
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		return entregaRepo.save(entrega);
	}
	
}
