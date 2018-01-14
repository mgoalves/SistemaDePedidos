package com.alves.model;

import java.util.Date;

import javax.persistence.Entity;

import com.alves.model.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoComBoleto extends Pagamento{
	
	private static final long serialVersionUID = 1L;

	//Atributos --------------------
	private Date dataVencimento;
	private Date dataPagamento;
	
	//Construtores ----------------
	public PagamentoComBoleto() {
	}
	public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}
	
	//Getters and Setters ---------
	@JsonFormat(pattern = "dd/MM/yyyy")
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	

	@JsonFormat(pattern = "dd/MM/yyyy")
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
}
