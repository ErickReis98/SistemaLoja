package br.com.sistemaloja.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaloja.domain.model.Entrega;
import br.com.sistemaloja.domain.model.StatusEntrega;
import br.com.sistemaloja.domain.repository.EntregaRepository;

@Service
public class EntregaService {

	private EntregaRepository entregaRepo;
	
	public EntregaService(EntregaRepository entregaRepo) {
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
