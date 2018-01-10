package com.alves.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.model.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		
		List<Categoria> lista = new ArrayList<>();
		
		lista.add(new Categoria(1L, "Moveis"));
		lista.add(new Categoria(2L, "Info"));
		
		return lista;
	}
	
	
}
