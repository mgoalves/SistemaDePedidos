package com.alves.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exception.ObjectNotFoundException;
import com.alves.model.Cliente;
import com.alves.repository.ClienteRepository;

@Service
public class ClienteService {
	
	//Injections
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	//Buscar por ID
	public Cliente findById(Long id){
		
		Cliente cliente = clienteRepository.findOne(id);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id  
					+ ", Tipo: " + Cliente.class.getName());
		}
		
		return cliente;
	}

}
