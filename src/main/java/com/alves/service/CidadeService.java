package com.alves.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exception.ObjectNotFoundException;
import com.alves.model.Cidade;
import com.alves.model.Estado;
import com.alves.repository.CidadeRepository;
import com.alves.repository.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	
	// Listar cidades com o ID de um determinado estados
	public List<Cidade> findAll(Long id) {
		
		Estado estado = estadoRepository.findOne(id);
		
		if(estado == null) {
			
			throw new ObjectNotFoundException("Estado não encontrado");
		}
		
		return cidadeRepository.findByEstadoEqualsOrderByNome(estado);
	}
}
