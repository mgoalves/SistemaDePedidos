package com.alves.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// Array que permite o usuário resgatar dados mesmo não estando logado. GET.
	private static final String[] PUBLIC_MATCHERS_GET = { 
			"/produtos/**", 
			"/categorias/**" ,
			"/h2-console/**",
			"/clientes/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.anyRequest().authenticated();
		
		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Mantem sem estado
		
		http.cors().and().csrf().disable(); // Para retirar defesa contra ataque de sessão
	}

	
	@Bean //Tipo de encriptação dos dados.
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
