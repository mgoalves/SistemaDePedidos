package com.alves.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.model.Categoria;
import com.alves.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		
		Categoria categoria = categoriaService.findById(id);
		return ResponseEntity.ok(categoria);
	}
	
	
}
