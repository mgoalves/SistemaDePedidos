package com.alves.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	private UserDetailsService detailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			UserDetailsService detailsService) {
		super(authenticationManager);

		this.jwtUtil = jwtUtil;
		this.detailsService = detailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String token = request.getHeader("Authorization");

		if (token != null && token.startsWith("Bearer ")) {

			UsernamePasswordAuthenticationToken auth = getAuthentication(token.substring(7));

			if (auth != null) {

				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {

		if (jwtUtil.isTokenValido(token)) {

			String username = jwtUtil.getUsername(token);

			if (username != null) {

				UserDetails user = detailsService.loadUserByUsername(username);
				return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			}
		}
		return null;
	}
	
}
