package com.alves.exception.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Possui lista de erros para formulários - Vários campos
 * 
 * @author alves
 *
 */
public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> erros = new ArrayList<>();
	
	//Contrutores -------------------------------
	public ValidationError() {
		super();
	}
	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	//Get and Set --------------------------------
	public List<FieldMessage> getErros() {
		return erros;
	}
	public void setErros(FieldMessage message) {
		this.erros.add(message);
	}
}
