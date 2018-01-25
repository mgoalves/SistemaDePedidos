package com.alves.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.alves.model.Cliente;
import com.alves.model.Pedido;

@Service
public class EmailService {
	
	@Autowired
	private MailSender mailSender;
	
	@Value("${default.sender}")
	private String sender;
	
	protected SimpleMailMessage prepareSimpleMailMessage(Pedido pedido) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(pedido.getCliente().getEmail());
		mail.setFrom(sender);
		mail.setSubject("Pedido confirmado. Seu código: " + pedido.getId());
		mail.setSentDate(new Date(System.currentTimeMillis()));
		mail.setText(pedido.toString());
		
		return mail;
	}

	public void send(Pedido pedido) {
		
		mailSender.send(prepareSimpleMailMessage(pedido));
	}

	public void sendNewPassword(Cliente cliente, String newPassword) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(cliente.getEmail());
		mail.setFrom(sender);
		mail.setSubject("Sua nova senha");
		mail.setSentDate(new Date(System.currentTimeMillis()));
		mail.setText("Sua nova senha é: " + newPassword);
		
		mailSender.send(mail);
		
	}

}
