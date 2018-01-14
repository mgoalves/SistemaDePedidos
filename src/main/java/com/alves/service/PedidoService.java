package com.alves.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exception.ObjectNotFoundException;
import com.alves.model.Pedido;
import com.alves.repository.PedidoRepository;

@Service
public class PedidoService {
	
	//Injections
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	//Buscar por ID
	public Pedido findById(Long id){
		
		Pedido pedido = pedidoRepository.findOne(id);
		
		if(pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id  
					+ ", Tipo: " + Pedido.class.getName());
		}
		
		return pedido;
	}

}
