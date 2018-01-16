package com.alves.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.alves.model.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	
	//Atributos -------------------------------------------------
	@Id
	private Long id;
	private Integer estado;
	
	
	@OneToOne @MapsId @JsonIgnore
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	
	//Constructors -----------------------------------------------
	public Pagamento(Long id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = (estado == null) ? null : estado.getCod();
		this.pedido = pedido;
	}
	public Pagamento() {
	}
	
	//Getters and Setters  ----------------------------------------
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}
	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	//HashCode and Equals: ID --------------------------------------
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
