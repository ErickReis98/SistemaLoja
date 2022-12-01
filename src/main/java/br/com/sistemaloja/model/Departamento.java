package br.com.sistemaloja.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;


public class Departamento {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDepartamento")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER)
	private List<Vendedor> vendedor;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonIgnore
	public List<Vendedor> getVendedor() {
		return vendedor;
	}

	@JsonIgnore
	public void setVendedor(List<Vendedor> vendedor) {
		this.vendedor = vendedor;
	}
	
	


	
	
	
}
