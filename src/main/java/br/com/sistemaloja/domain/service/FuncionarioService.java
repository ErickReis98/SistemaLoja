package br.com.sistemaloja.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaloja.domain.model.Funcionario;
import br.com.sistemaloja.domain.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	private FuncionarioRepository funcRepo;

	public FuncionarioService(FuncionarioRepository funcRepo) {
		super();
		this.funcRepo = funcRepo;
	}
	
	@Transactional
	public Funcionario adicionar(Funcionario func) {
		return funcRepo.save(func);
	}

	@Transactional
	public List<Funcionario> listarTodos(){
		return funcRepo.findAll();
	}

	@Transactional
	public Funcionario alterar(Funcionario func, Long funcId) {
	 func.setId(funcId);
	 return func = funcRepo.save(func);
	}
	
	@Transactional
	public void deletar(Long funcId) {
		funcRepo.deleteById(funcId);
	}
	
	@Transactional
	public List<Object> findByDepartamento(Long depId){
		return funcRepo.findByDepContaning(depId);
	}
	
	@Transactional
	public List<Object> findByNome(String nome){
		return funcRepo.findByNomeContaining(nome);
	}
	
	
}
