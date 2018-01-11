package com.alves.exception.handler;

import java.io.Serializable;

public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Atributos
	private Integer status;
	private String msg;
	private Long timeStamp;
	
	
	//Contructor
	public StandardError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}


	//Getters and Setters
	public int getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
