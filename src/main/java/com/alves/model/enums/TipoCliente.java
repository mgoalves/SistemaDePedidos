package com.alves.model.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	//Atributos -----------------------------------
	private int id;
	private String descrição;

	//Constructor ---------------------------------
	private TipoCliente(int id, String descrição) {
		this.id = id;
		this.descrição = descrição;
	}

	//Getters -------------------------------------
	public int getId() {
		return id;
	}
	public String getDescrição() {
		return descrição;
	}
	
	//Métodos auxiliares --------------------------
	public static TipoCliente toEnum(Integer id) {
		
		if(id == null) {
			return null;
		}
		
		for(TipoCliente tipo : TipoCliente.values()) {
			
			if(id.equals(tipo.getId())) {
				
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("ID: " + id  + " inválido.");
	}
}
