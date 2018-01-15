package com.alves.model.dto;

import java.io.Serializable;

import com.alves.model.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	//Atributos -------------------------------
	private Long id;
	private String nome;
	private String email;


	//Construtores -------------------------------
	public ClienteDTO() {
	}
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
