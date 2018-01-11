package com.alves.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exception.ObjectNotFoundException;
import com.alves.model.Categoria;
import com.alves.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	//Injections
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	//Buscar por ID
	public Categoria findById(Long id){
		
		Categoria categoria = categoriaRepository.findOne(id);
		
		if(categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id  
					+ ", Tipo: " + Categoria.class.getName());
		}
		
		return categoria;
	}

}
