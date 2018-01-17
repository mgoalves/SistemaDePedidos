package com.alves.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.alves.exception.handler.FieldMessage;
import com.alves.model.Cliente;
import com.alves.model.dto.ClienteDTO;
import com.alves.repository.ClienteRepository;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdateAnn, ClienteDTO> {

	
	//Injeções -----------------------------------
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdateAnn ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Outro usuário já tem esse email cadastrado."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getErro()).addPropertyNode(e.getFieldErro()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}