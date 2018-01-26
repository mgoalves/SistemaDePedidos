package com.alves.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	private String secret = "1d6790f70d5c76734c617ed29f693d5c6720bc96691e1ec36f34fd23666e321872173f54eff6c7d0eb27cd4d7a16433ccd3b9502f9457c2ba1ba62926c69cc0f";
	private Long expiration = (long) 60000 * 60 * 24; // 1 Dia

	public String generateToken(String username) {

		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean isTokenValido(String token) {

		Claims claims = getClaims(token);

		if (claims != null) {

			String username = claims.getSubject();
			Date expiration = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());

			if (username != null && expiration != null && now.before(expiration)) {

				return true;
			}
		}

		return false;
	}

	public String getUsername(String token) {

		Claims claims = getClaims(token);

		if (claims != null) {

			String username = claims.getSubject();
			
			if (username != null) {

				return username;
			}
		}
		return null;
	}

	private Claims getClaims(String token) {

		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();

		} catch (Exception e) {

			return null;
		}
	}

}
