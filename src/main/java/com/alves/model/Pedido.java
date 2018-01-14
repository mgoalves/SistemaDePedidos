package com.alves.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	
	//Atributos ------------------------------------------------
	private Long id;
	private Date instante;
	
	private Pagamento pagamento;
	private Cliente cliente;
	private Endereco endDeEntrega;
	private Set<ItemPedido> itens = new HashSet<>();
	
	//Construtores ----------------------------------------------
	public Pedido() {
	}
	public Pedido(Long id, Date instante, Cliente cliente, Endereco endDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.endDeEntrega = endDeEntrega;
	}
	
	//Getters and Setters ----------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	public Date getInstante() {
		return instante;
	}
	public void setInstante(Date instante) {
		this.instante = instante;
	}
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	public Endereco getEndDeEntrega() {
		return endDeEntrega;
	}
	public void setEndDeEntrega(Endereco endDeEntrega) {
		this.endDeEntrega = endDeEntrega;
	}
	
	@OneToMany(mappedBy = "id.pedido")
	public Set<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	
	//HashCode and Equals: ID ---------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
