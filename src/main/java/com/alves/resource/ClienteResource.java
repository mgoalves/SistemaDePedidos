package com.alves.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alves.model.Cliente;
import com.alves.model.dto.ClienteDTO;
import com.alves.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	//Injeções ---------------------------
	@Autowired
	private ClienteService clienteService;

	
	//Endpoints -----------------------------------------------------------------------------------
	
	//Buscar um - GET -----------------------------------------
	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Cliente> findOne(@PathVariable Long id) {
		
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok(cliente);
	}

	
	//Atualizar - PUT ------------------------------------------
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id, 
									   @Valid @RequestBody ClienteDTO clienteDTO) {
		
		clienteService.update(id, clienteDTO);
		return ResponseEntity.noContent().build();
	}
	
	
	//Deletar - DELETE ------------------------------------------
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Cliente> delete(@PathVariable Long id) {
		
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	
	//Listar - GET ------------------------------------------------
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<ClienteDTO> list = clienteService.findAll();
		return ResponseEntity.ok(list);
	}
	
	
	//Listar com Paginação - GET -----------------------------------
	@GetMapping
	@RequestMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> pageAll(
			@RequestParam(value="page", defaultValue="0") 	Integer page, 
			@RequestParam(value="size", defaultValue="24")  Integer size, 
			@RequestParam(value="orderBy", defaultValue="nome")  String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		return ResponseEntity.ok(clienteService.pageAll(page, size, direction, orderBy));
	}
}
