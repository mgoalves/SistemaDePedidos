package com.alves.model;

import java.io.Serializable;


public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Atributos -------------------------
	private Long id;
	private String nome;
	
	//Getters and Setters ---------------
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
	
	//Constructor -----------------------
	public Categoria(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public Categoria() {
	}
	
	//Equals and HashCode ----------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
