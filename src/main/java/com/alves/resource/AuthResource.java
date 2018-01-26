package com.alves.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alves.model.dto.EmailDTO;
import com.alves.security.JWTUtil;
import com.alves.security.UserSistem;
import com.alves.service.AuthService;
import com.alves.service.UserService;

@RestController
@RequestMapping("auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		
		UserSistem user = UserService.getAuthenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@RequestMapping("/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDto){
		
		authService.forgot(emailDto.getEmail());
		
		return ResponseEntity.noContent().build();
	}
}
