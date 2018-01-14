package com.alves.model.dto;

import java.io.Serializable;

import com.alves.model.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	//Atributos -----------------------------------
	private Long id;
	private String nome;
	
	
	//Construtores --------------------------------
	public CategoriaDTO() {
	}
	public CategoriaDTO(Categoria categoria) {
		super();
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
	
	//Getters and Setters ------------------------
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
}
