package com.alves.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alves.exception.ObjectNotFoundException;
import com.alves.model.Categoria;
import com.alves.model.Produto;
import com.alves.repository.CategoriaRepository;
import com.alves.repository.ProdutoRepository;

@Service
public class ProdutoService {

	//Injections --------------------------------
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Buscar por ID --------------------------------------------------
	public Produto findById(Long id){
		
		Produto produto = produtoRepository.findOne(id);
		
		if(produto == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID: " + id  
					+ ", Tipo: " + Produto.class.getName());
		}
		
		return produto;
	}
	
	// Buscar produtos baseados no nome -----------------------------
	public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer size, String direction, String orderBy){
		
		PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest); 
	}
}
