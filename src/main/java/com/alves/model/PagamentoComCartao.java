package com.alves.model;

import javax.persistence.Entity;

import com.alves.model.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	//Atributos -------------------------------------
	private Integer numeroDeParcelas;
	
	//Construtores -----------------------------------
	public PagamentoComCartao() {
	}
	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	//Get and Set -------------------------------------
	public Integer getnumeroDeParcelas() {
		return numeroDeParcelas;
	}
	public void setnumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
}
