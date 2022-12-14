package br.com.sistemaloja.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;

@Entity
@Table(name = "departamento")
public class Departamento {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_departamento")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER)
	private List<Funcionario> funcionario;

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
	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	@JsonIgnore
	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

}
