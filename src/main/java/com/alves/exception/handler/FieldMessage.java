package com.alves.exception.handler;

import java.io.Serializable;

/**
 * Classe que é resposavel por guardar os dados de validações de campos
 * 
 * @author alves
 *
 */
public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	//Atributos ---------------------------------------
	private String fieldErro;
	private String erro;
	
	//Construtores -------------------------------------
	public FieldMessage() {
	}
	public FieldMessage(String fieldErro, String erro) {
		super();
		this.fieldErro = fieldErro;
		this.erro = erro;
	}
	
	//Getters and Setters ------------------------------
	public String getFieldErro() {
		return fieldErro;
	}
	public void setFieldErro(String fieldErro) {
		this.fieldErro = fieldErro;
	}
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
}
