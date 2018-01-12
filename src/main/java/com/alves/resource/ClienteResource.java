package com.alves.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.model.Cliente;
import com.alves.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	//Injeções ---------------------------
	@Autowired
	private ClienteService clienteService;

	
	//Endpoints --------------------------
	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		
		Cliente categoria = clienteService.findById(id);
		return ResponseEntity.ok(categoria);
	}
	
}
