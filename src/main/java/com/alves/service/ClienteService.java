package com.alves.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alves.exception.ObjectNotFoundException;
import com.alves.model.Cliente;
import com.alves.model.dto.ClienteDTO;
import com.alves.repository.ClienteRepository;

@Service
public class ClienteService {

	// Injections
	@Autowired
	private ClienteRepository clienteRepository;

	// Buscar por ID
	public Cliente findById(Long id) {

		Cliente cliente = clienteRepository.findOne(id);

		if (cliente == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. ID: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return cliente;
	}

	// Atualiza um cliente ---------------------------------------
	public Cliente update(Long id, Cliente cliente) {

		findById(id);
		cliente.setId(id);
		Cliente cli = clienteRepository.save(cliente);

		return cli;
	}

	// Deleta um cliente ------------------------------------------
	public void delete(Long id) {

		findById(id);
		try {
			clienteRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Esta categoria possui dados vinculados. Não é possível excluir.");
		}
	}

	// Listar todas os clientes --------------------------------------
	public List<ClienteDTO> findAll() {

		List<Cliente> list = clienteRepository.findAll();
		List<ClienteDTO> listDto = list.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
		return listDto;
	}

	// Lista com paginação ---------------------------------------------
	public Page<ClienteDTO> pageAll(Integer page, Integer size, String direction, String orderBy) {

		PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(direction), orderBy);

		Page<Cliente> list = clienteRepository.findAll(pageRequest);
		Page<ClienteDTO> listDto = list.map(cliente -> new ClienteDTO(cliente));

		return listDto;

	}
}
