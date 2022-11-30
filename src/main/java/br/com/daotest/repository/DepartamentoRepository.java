package br.com.daotest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.daotest.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

	

	List<Departamento> findAll();

	List<Departamento> findByNomeContaining(String string);
	

}
