package com.alves.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alves.exception.ObjectNotFoundException;
import com.alves.model.Categoria;
import com.alves.model.dto.CategoriaDTO;
import com.alves.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	//Injections
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	//Buscar por ID -----------------------------------------------
	public Categoria findById(Long id){
		
		Categoria categoria = categoriaRepository.findOne(id);
		
		if(categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id  
					+ ", Tipo: " + Categoria.class.getName());
		}
		
		return categoria;
	}


	//Salvar uma nova categoria -----------------------------------
	public Categoria save(Categoria categoria) {
		
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}


	//Atualiza uma categoria ---------------------------------------
	public Categoria update(Long id, Categoria categoria) {
		
		findById(id);
		categoria.setId(id);
		Categoria cat = categoriaRepository.save(categoria);
		
		return cat;
	}

	// Deleta uma categoria -----------------------------------------
	public void delete(Long id) {
		
		findById(id);
		
		try {
			categoriaRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Esta categoria possui produtos vinculados. Não é possível excluir.");
		}
	}


	// Lista todas as categorias ------------------------------------
	/**
	 * @param none
	 * @return CategoriaDTO
	 */
	public List<CategoriaDTO> findAll() {
		
		List<Categoria> list = categoriaRepository.findAll();
		List<CategoriaDTO> listDto = list.stream()
										.map(categoria -> new CategoriaDTO(categoria))
										.collect(Collectors.toList());
		return listDto;
	}
	
	// Lista as categorias por paginação -----------------------------
	public Page<CategoriaDTO> pageAll(Integer page, Integer size, String direction, String orderBy){
		
		PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(direction), orderBy);
		
		Page<Categoria> list = categoriaRepository.findAll(pageRequest);
		Page<CategoriaDTO> listDto = list.map(categoria -> new CategoriaDTO(categoria)); 
		
		return listDto; 
	}
}
