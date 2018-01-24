package com.alves.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	private String secret = "1d6790f70d5c76734c617ed29f693d5c6720bc96691e1ec36f34fd23666e321872173f54eff6c7d0eb27cd4d7a16433ccd3b9502f9457c2ba1ba62926c69cc0f"; 
	private Long expiration = (long) 60000; 
	
	public String generateToken(String username) {
		
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512 , secret.getBytes())
				.compact();
	}
}
