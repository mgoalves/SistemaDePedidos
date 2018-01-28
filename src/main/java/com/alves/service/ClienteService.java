package com.alves.service;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.InvalidAttributeValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alves.exception.ObjectNotFoundException;
import com.alves.model.Cidade;
import com.alves.model.Cliente;
import com.alves.model.Endereco;
import com.alves.model.dto.ClienteDTO;
import com.alves.model.dto.ClienteTelEndDTO;
import com.alves.model.enums.Perfil;
import com.alves.model.enums.TipoCliente;
import com.alves.repository.CidadeRepository;
import com.alves.repository.ClienteRepository;
import com.alves.repository.EnderecoRepository;
import com.alves.security.UserSistem;

@Service
public class ClienteService {

	// Injections
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImageService imageService;

	// Buscar por ID --------------------------------------------
	public Cliente findById(Integer id) {

		UserSistem user = UserService.getAuthenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			
			throw new AuthorizationServiceException("Acesso negado");
		}
		
		Cliente cliente = clienteRepository.findOne(id);

		if (cliente == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. ID: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return cliente;
	}
	
	// Salvar -----------------------------------------------------
	public Cliente save(ClienteTelEndDTO clienteTelEndDTO) throws InvalidAttributeValueException {
		
		Cidade cidade = cidadeRepository.findOne(clienteTelEndDTO.getCidadeId());
		
		Cliente cliente = new Cliente(null, clienteTelEndDTO.getNome(), clienteTelEndDTO.getEmail(), 
										clienteTelEndDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteTelEndDTO.getTipo()), 
										passwordEncoder.encode(clienteTelEndDTO.getSenha()));

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
	public Cliente update(Integer id, ClienteDTO clienteDTO) {

		Cliente cliente = findById(id);
		updateData(cliente, clienteDTO);
		cliente = clienteRepository.save(cliente);

		return cliente;
	}

	
	// Deleta um cliente ------------------------------------------
	public void delete(Integer id) {

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
	
	//Salva foto de perfil do usuário -----------------------------------
	public URI uploadProfile(MultipartFile file) {
		
		UserSistem user = UserService.getAuthenticated();
		if(user == null) {
			
			throw new AuthorizationServiceException("Acesso negado");
		}
		
		BufferedImage image = imageService.getJpgImageFromFile(file);
		image = imageService.cropSquare(image);
		image = imageService.resize(image);
		
		String name = user.getId() + ".jpg";
		
		InputStream ins = imageService.getInputStream(image, "jpg");
		
		return s3Service.uploadFile(name, ins, "image");
	}
	
	// Buscar por Email --------------------------------------------
	public Cliente findByEmail(String email) {

		UserSistem user = UserService.getAuthenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			
			throw new AuthorizationServiceException("Acesso negado");
		}
		
		Cliente cliente = clienteRepository.findByEmail(email);

		if (cliente == null) {
			
			throw new ObjectNotFoundException(
					"Objeto não encontrado. E-mail: " + email + ", Tipo: " + Cliente.class.getName());
		}
		
		return cliente;
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
