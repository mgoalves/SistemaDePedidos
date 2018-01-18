package com.alves.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alves.model.Produto;
import com.alves.resource.util.Util;
import com.alves.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	//Injeções ---------------------------
	@Autowired
	private ProdutoService produtoService;

	
	//Endpoints -------------------------------------------------
	
	//Busca por id ------------------------------------------------
	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Produto> findOne(@PathVariable Long id) {
		
		Produto produto = produtoService.findById(id);
		return ResponseEntity.ok(produto);
	}
	
	//Buscar com paginação --------------------------------------------------
	@GetMapping
	public ResponseEntity<Page<Produto>> pageAll(
			@RequestParam(value="nome", defaultValue="") 	String nome, 
			@RequestParam(value="categorias", defaultValue="")  String categorias, 
			@RequestParam(value="page", defaultValue="0") 	Integer page, 
			@RequestParam(value="size", defaultValue="24")  Integer size, 
			@RequestParam(value="orderBy", defaultValue="nome")  String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		List<Long> ids = Util.stringToLong(categorias);
		nome = Util.decodeString(nome);
		
		return ResponseEntity.ok(produtoService.search(nome, ids, page, size, direction, orderBy));
	}
}
