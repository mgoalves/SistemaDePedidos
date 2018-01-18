package com.alves.resource.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public static List<Long> stringToLong(String s){
		
		if(s == null || s.isEmpty()) {
			return null;
		}
		
		String[] vetor = s.split(",");
		List<Long> lista = new ArrayList<>();
		
		for (int i = 0; i < vetor.length; i++) {
			
			lista.add(Long.parseLong(vetor[i]));
		}
		
		return lista;
	}
	
	public static String decodeString(String s) {
		
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
