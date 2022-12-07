package br.com.sistemaloja.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sistemaloja.domain.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	// quando puxar id de outra classe(chave estrangeira) usar ponto . como separador na sintaxe da query
	// não necessario usar o JOIN
	@Query(value = "select f.nome, f.email, f.salario, f.data_nascimento, f.departamento.nome from Funcionario f  where f.departamento.id = :id_departamento")
	List<Object> findByDepContaning(@Param("id_departamento") Long id_departamento);

	@Query(value = "select f.nome, f.email, f.salario, f.departamento.id from Funcionario f where f.nome like %:nome%")
	List<Object> findByNomeContaining(@Param("nome") String nome);

}
