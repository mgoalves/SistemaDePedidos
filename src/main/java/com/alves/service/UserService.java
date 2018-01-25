package com.alves.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alves.security.UserSistem;

@Service
public class UserService {

	public static UserSistem getAuthenticated() {
		
		try {
			return (UserSistem) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
		} catch (Exception e) {
			
			return null;
		}
	}
	
}
