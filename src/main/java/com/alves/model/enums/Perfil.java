package com.alves.model.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	//Atributos -----------------------------------
	private int id;
	private String descrição;

	//Constructor ---------------------------------
	private Perfil(int id, String descrição) {
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
	public static Perfil toEnum(Integer id) {
		
		if(id == null) {
			return null;
		}
		
		for(Perfil tipo : Perfil.values()) {
			
			if(id.equals(tipo.getId())) {
				
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("ID: " + id  + " inválido.");
	}
}
