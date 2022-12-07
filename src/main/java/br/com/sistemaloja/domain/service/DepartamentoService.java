package br.com.sistemaloja.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaloja.domain.model.Departamento;
import br.com.sistemaloja.domain.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	DepartamentoRepository depRepo;

	public DepartamentoService(DepartamentoRepository depRepo) {
		super();
		this.depRepo = depRepo;
	}
	
	@Transactional
	public Departamento adicionar(Departamento dep) {
		return depRepo.save(dep);
	}	
	
	@Transactional
	public List<Departamento> listarTodos(){
		return depRepo.findAll();
	}
	
	@Transactional
	public Departamento alterar(Departamento dep, Long id) {
		dep.setId(id);
		return dep = depRepo.save(dep);
	}
	
	@Transactional
	public void deletar(Long depId) {
		depRepo.deleteById(depId);
	}
}
