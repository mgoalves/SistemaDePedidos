package com.alves.model.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.alves.model.Cliente;
import com.alves.service.validation.ClienteUpdateAnn;

@ClienteUpdateAnn
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	//Atributos -------------------------------
	private Integer id;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Length(min = 2, max = 40, message = "Tamanho inválido.")
	public String getNome() {
		return nome;
	}	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Email(message = "Email Inválido.") @Length(min = 5, max = 40, message = "Tamanho inválido.")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
