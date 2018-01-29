package com.alves.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.model.Estado;
import com.alves.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;

	// Buscar estados ordenados por nome -------------
	public List<Estado> findAll() {
	
		return estadoRepository.findAllByOrderByNome();
	}

}
