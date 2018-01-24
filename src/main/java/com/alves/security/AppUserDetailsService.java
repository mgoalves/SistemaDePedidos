package com.alves.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alves.model.Cliente;
import com.alves.repository.ClienteRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if (cliente == null) {
			throw new UsernameNotFoundException("Usuário e/ou senha incorretos");
		}
		
		return new UserSistem(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}
}
