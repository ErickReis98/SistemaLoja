package br.com.sistemaloja.domain.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sistemaloja.domain.controller.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	//quando puxar id de outra classe(chave estrangeira) usar ponto . como separador na sintaxe da query
	// não necessario usar o JOIN
	@Query(value = "select v.nome, v.email, v.salario, v. data_nascimento, v.departamento.nome from Funcionario v  where v.departamento.id = :departamento_id")
	List<Object> findByDepContaning(@Param("departamento_id") Long departamento_id);
	 
	@Query(value = "select v.nome, v.email, v.salario, v.departamento.id, v.departamento.nome from Funcionario v where v.nome like %:nome%")
	List<Object> findByNomeContaining(@Param("nome")String nome);
	

}
