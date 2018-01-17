package com.alves.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.alves.exception.handler.FieldMessage;
import com.alves.model.Cliente;
import com.alves.model.dto.ClienteTelEndDTO;
import com.alves.model.enums.TipoCliente;
import com.alves.repository.ClienteRepository;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsertAnn, ClienteTelEndDTO> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsertAnn ann) {
	}

	@Override
	public boolean isValid(ClienteTelEndDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getId()) && !ValidaCPFeCNPJ.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getId()) && !ValidaCPFeCNPJ.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente."));
		}
		aux = null;
		aux = clienteRepository.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		if (aux != null) {
			
			if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getId())) {
				list.add(new FieldMessage("cpfOuCnpj", "CPF já cadastrado."));
			}
			if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getId())) {
				list.add(new FieldMessage("cpfOuCnpj", "CNPJ já cadastrado."));
			}
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getErro()).addPropertyNode(e.getFieldErro()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}