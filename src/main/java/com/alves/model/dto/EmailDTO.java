package com.alves.model.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Email(message = "Email Inválido.") @Length(min = 5, max = 40, message = "Tamanho inválido.")
	private String email;

	public EmailDTO() {
	}	
	
	//Get and Set ------------------------
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
