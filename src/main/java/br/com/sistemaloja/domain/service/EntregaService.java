package br.com.sistemaloja.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaloja.domain.model.Cliente;
import br.com.sistemaloja.domain.model.Entrega;
import br.com.sistemaloja.domain.model.StatusEntrega;
import br.com.sistemaloja.domain.repository.EntregaRepository;

@Service
public class EntregaService {

	private EntregaRepository entregaRepo;
	private ClienteService clienteServ;

	public EntregaService(EntregaRepository entregaRepo, ClienteService clienteServ) {
		super();
		this.entregaRepo = entregaRepo;
		this.clienteServ = clienteServ;
	}

	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = clienteServ.buscarId(entrega.getCliente().getId());

		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());

		return entregaRepo.save(entrega);
	}
	
	public List<Entrega> listar() {
		return entregaRepo.findAll();
	}

	public Entrega buscaId(Long id) {
		return entregaRepo.findById(id);
	}
}
