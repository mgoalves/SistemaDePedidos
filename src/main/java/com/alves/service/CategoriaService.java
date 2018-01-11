package com.alves.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.model.Categoria;
import com.alves.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	//Injections
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	//Buscar por ID
	public Categoria findById(Long id){
		
		return categoriaRepository.findOne(id);
	}

}
