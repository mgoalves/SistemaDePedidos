package com.alves.exception.handler;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> erros = new ArrayList<>();
	
	//Contrutores -------------------------------
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}
	public ValidationError() {
		super();
	}

	//Get and Set --------------------------------
	public List<FieldMessage> getErros() {
		return erros;
	}
	public void setErros(FieldMessage message) {
		this.erros.add(message);
	}
}
