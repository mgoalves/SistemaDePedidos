package com.alves.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alves.model.Categoria;
import com.alves.model.dto.CategoriaDTO;
import com.alves.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	
	@Autowired
	private CategoriaService categoriaService;

	//Buscar --------------------------------------------------------------
	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Categoria> findOne(@PathVariable Long id) {
		
		Categoria categoria = categoriaService.findById(id);
		return ResponseEntity.ok(categoria);
	}
	
	//Salvar ---------------------------------------------------------------
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Categoria categoria){
		
		categoria = categoriaService.save(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//Atualizar -----------------------------------------------------------
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id,  @Valid @RequestBody CategoriaDTO categoriaDTO) {
		
		categoriaService.update(id, categoriaDTO);
		return ResponseEntity.noContent().build();
	}
	
	//Deletar categoria ----------------------------------------------------
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Categoria> delete(@PathVariable Long id) {
		
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//Listar todas categorias ------------------------------------------------
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		
		List<CategoriaDTO> list = categoriaService.findAll();
		return ResponseEntity.ok(list);
	}
	
	//Listar com paginação --------------------------------------------------
	@GetMapping
	@RequestMapping("/page")
	public ResponseEntity<Page<CategoriaDTO>> pageAll(
			@RequestParam(value="page", defaultValue="0") 	Integer page, 
			@RequestParam(value="size", defaultValue="24")  Integer size, 
			@RequestParam(value="orderBy", defaultValue="nome")  String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		return ResponseEntity.ok(categoriaService.pageAll(page, size, direction, orderBy));
	}
}
