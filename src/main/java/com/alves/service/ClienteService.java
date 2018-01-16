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
import com.alves.model.Cidade;
import com.alves.model.Cliente;
import com.alves.model.Endereco;
import com.alves.model.dto.ClienteDTO;
import com.alves.model.dto.ClienteTelEndDTO;
import com.alves.model.enums.TipoCliente;
import com.alves.repository.CidadeRepository;
import com.alves.repository.ClienteRepository;
import com.alves.repository.EnderecoRepository;

@Service
public class ClienteService {

	// Injections
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	// Buscar por ID --------------------------------------------
	public Cliente findById(Long id) {

		Cliente cliente = clienteRepository.findOne(id);

		if (cliente == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. ID: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return cliente;
	}
	
	// Salvar -----------------------------------------------------
	public Cliente save(ClienteTelEndDTO clienteTelEndDTO) {
		
		Cidade cidade = cidadeRepository.findOne(clienteTelEndDTO.getCidadeId());
		
		Cliente cliente = new Cliente(null, clienteTelEndDTO.getNome(), clienteTelEndDTO.getEmail(), 
										clienteTelEndDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteTelEndDTO.getTipo()));

		Endereco endereco =  new Endereco(null, clienteTelEndDTO.getLogradouro(), clienteTelEndDTO.getNumero(), 
							clienteTelEndDTO.getComplemento(), clienteTelEndDTO.getBairro(), clienteTelEndDTO.getCep(), cliente, cidade);
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteTelEndDTO.getTelefone1());
		
		if(clienteTelEndDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteTelEndDTO.getTelefone2());
		}
		
		cliente = clienteRepository.save(cliente);
		enderecoRepository.save(endereco);
		
		return cliente;
	}


	// Atualiza um cliente ---------------------------------------
	public Cliente update(Long id, ClienteDTO clienteDTO) {

		Cliente cliente = findById(id);
		updateData(cliente, clienteDTO);
		cliente = clienteRepository.save(cliente);

		return cliente;
	}

	
	// Deleta um cliente ------------------------------------------
	public void delete(Long id) {

		findById(id);
		try {
			clienteRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Este cliente possui dados vinculados. Não é possível excluir.");
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
	
	@SuppressWarnings("unused")
	private Cliente fromDTO(ClienteDTO clienteDTO) {
		
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}


	private void updateData(Cliente cliente, ClienteDTO clienteDTO) {
		
		if(clienteDTO.getNome() != null) {
			cliente.setNome(clienteDTO.getNome());
		}
		if(clienteDTO.getEmail() != null) {
			cliente.setEmail(clienteDTO.getEmail());
		}
	}
}
