package br.com.daotest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.daotest.model.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

	//quando puxar id de outra classe(chave estrangeira) usar ponto . como separador na sintaxe da query
	// não necessario usar o JOIN
	@Query(value = "select v.nome, v.email, v.salario, v. data_nascimento, v.departamento.nome from Vendedor v  where v.departamento.id = :id_departamento")
	List<Object> findByDepContaning(@Param("id_departamento") Long id_departamento);
	 
	@Query(value = "select v.nome, v.email, v.salario, v.departamento.id from Vendedor v where v.nome like %:nome%")
	List<Object> findByNomeContaining(@Param("nome")String nome);
	

}
