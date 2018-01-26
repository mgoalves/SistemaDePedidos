package com.alves;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alves.service.S3Service;

@SpringBootApplication
public class SistemaDePedidosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDePedidosApplication.class, args);
	}
	
	@Autowired
	private S3Service amazon;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		amazon.uploadFile("/home/alves/Pictures/Wallpapers/3.jpg");
	}
}
 