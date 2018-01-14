package com.alves.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alves.model.Categoria;
import com.alves.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Categoria> findOne(@PathVariable Long id) {
		
		Categoria categoria = categoriaService.findById(id);
		return ResponseEntity.ok(categoria);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria){
		
		categoria = categoriaService.save(categoria); // TODO testar endpoint de salvar.
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	@RequestMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Categoria categoria) {
		
		categoriaService.update(id, categoria);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
