package com.alves.model.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	//Atributos -----------------------------------
	private int cod;
	private String descrição;

	//Constructor ---------------------------------
	private EstadoPagamento(int cod, String descrição) {
		this.cod = cod;
		this.descrição = descrição;
	}

	//Getters -------------------------------------
	public int getCod() {
		return cod;
	}
	public String getDescrição() {
		return descrição;
	}
	
	//Métodos auxiliares --------------------------
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(EstadoPagamento tipo : EstadoPagamento.values()) {
			
			if(cod.equals(tipo.getCod())) {
				
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("ID: " + cod  + " inválido.");
	}
}
