package com.alves.model;

import javax.persistence.Entity;

import com.alves.model.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{

	private static final long serialVersionUID = 1L;

	//Atributos -------------------------------------
	private Integer numDeParcelas;
	
	//Construtores -----------------------------------
	public PagamentoComCartao() {
	}
	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numDeParcelas) {
		super(id, estado, pedido);
		
		this.numDeParcelas = numDeParcelas;
	}
	
	//Get and Set -------------------------------------
	public Integer getNumDeParcelas() {
		return numDeParcelas;
	}
	public void setNumDeParcelas(Integer numDeParcelas) {
		this.numDeParcelas = numDeParcelas;
	}
}
