package com.alves.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.model.Cidade;
import com.alves.model.Estado;
import com.alves.service.CidadeService;
import com.alves.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CidadeService cidadeService;

	// Listar todos os estados -----------------------------
	@GetMapping
	public ResponseEntity<List<Estado>> listAll() {

		List<Estado> estados = estadoService.findAll();
		return ResponseEntity.ok(estados);
	}

	// Listar todas as cidades de um determinado estado ----
	@GetMapping
	@RequestMapping("/{id}/cidades")
	public ResponseEntity<List<Cidade>> listAllCities(@PathVariable Long id) {

		List<Cidade> cidades = cidadeService.findAll(id);
		return ResponseEntity.ok(cidades);
	}

}
