package com.alves.resource;

import java.net.URI;
import java.util.List;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alves.model.Cliente;
import com.alves.model.dto.ClienteDTO;
import com.alves.model.dto.ClienteTelEndDTO;
import com.alves.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	// Injeções ---------------------------
	@Autowired
	private ClienteService clienteService;

	// Endpoints
	// -----------------------------------------------------------------------------------

	// Buscar por ID- GET -----------------------------------------
	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Cliente> findOne(@PathVariable Integer id) {

		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok(cliente);
	}

	// Salvar ---------------------------------------------------------------
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteTelEndDTO ClienteTelEndDTO)
			throws InvalidAttributeValueException {

		Cliente cliente = clienteService.save(ClienteTelEndDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	// Atualizar - PUT ------------------------------------------
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {

		clienteService.update(id, clienteDTO);
		return ResponseEntity.noContent().build();
	}

	// Deletar - DELETE ------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {

		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Listar - GET ------------------------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<ClienteDTO> list = clienteService.findAll();
		return ResponseEntity.ok(list);
	}

	// Listar com Paginação - GET -----------------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	@RequestMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> pageAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		return ResponseEntity.ok(clienteService.pageAll(page, size, direction, orderBy));
	}

	// Enviar foto de perfil
	// ---------------------------------------------------------------
	@PostMapping
	@RequestMapping("/picture")
	public ResponseEntity<Void> uploadProfile(@RequestParam(name = "file") MultipartFile file) {

		URI uri = clienteService.uploadProfile(file);

		return ResponseEntity.created(uri).build();
	}

	// Buscar por Email - GET -----------------------------------------
	@GetMapping
	@RequestMapping("/email")
	public ResponseEntity<ClienteDTO> findOne(@RequestParam("value") String email) {

		Cliente cliente = clienteService.findByEmail(email);
		return ResponseEntity.ok(new ClienteDTO(cliente));
	}
}
