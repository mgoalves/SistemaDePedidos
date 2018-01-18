package com.alves.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alves.model.Pedido;
import com.alves.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	// Injeções ---------------------------
	@Autowired
	private PedidoService pedidoService;

	// Endpoints --------------------------

	// Buscar um pedido -----------------------------------------------
	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Pedido> findOne(@PathVariable Long id) {

		Pedido categoria = pedidoService.findById(id);
		return ResponseEntity.ok(categoria);
	}

	// Salvar ----------------------------------------------------------
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {

		pedido = pedidoService.save(pedido);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
