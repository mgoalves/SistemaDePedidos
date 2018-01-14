package com.alves.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.model.Pedido;
import com.alves.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	//Injeções ---------------------------
	@Autowired
	private PedidoService pedidoService;

	
	//Endpoints --------------------------
	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		
		Pedido categoria = pedidoService.findById(id);
		return ResponseEntity.ok(categoria);
	}
	
}
