package com.alves.exception.handler;

import javax.management.InvalidAttributeValueException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alves.exception.FileException;
import com.alves.exception.ObjectNotFoundException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	// Trata dados que não foram encontrados
	// -------------------------------------------
	@org.springframework.web.bind.annotation.ExceptionHandler({ ObjectNotFoundException.class })
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}

	// Trata exceções quando entidades vinculadas não foram encontradas
	// --------------------------------
	@org.springframework.web.bind.annotation.ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e,
			WebRequest request) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Trata parametros com dados inválidos
	// ------------------------------------------------------------
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Argumento(s) inválido(s).",
				System.currentTimeMillis());

		for (FieldError erro : e.getBindingResult().getFieldErrors()) {

			error.setErros(new FieldMessage(erro.getField(), erro.getDefaultMessage()));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Trata exceções quando entidades vinculadas não foram encontradas
	// --------------------------------
	@org.springframework.web.bind.annotation.ExceptionHandler({ InvalidAttributeValueException.class })
	public ResponseEntity<Object> InvalidAttributeValueException(InvalidAttributeValueException e, WebRequest request) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Trata exceções quando existe permissão de acesso negada.
	// --------------------------------
	@org.springframework.web.bind.annotation.ExceptionHandler({ AuthorizationServiceException.class })
	public ResponseEntity<Object> AuthorizationServiceException(AuthorizationServiceException e, WebRequest request) {

		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

	// Trata exceções quando existir erros na conversão de arquivo
	// --------------------------------
	@org.springframework.web.bind.annotation.ExceptionHandler({ FileException.class })
	public ResponseEntity<Object> fileException(FileException e, WebRequest request) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Trata exceções AMAZON - 1.
	// --------------------------------
	@org.springframework.web.bind.annotation.ExceptionHandler({ AmazonServiceException.class })
	public ResponseEntity<Object> amazonServiceException(AmazonServiceException e, WebRequest request) {

		HttpStatus statusCode = HttpStatus.valueOf(e.getErrorCode());
		
		StandardError error = new StandardError(statusCode.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(statusCode).body(error);
	}

	// Trata exceções AMAZON - 2.
	// --------------------------------
	@org.springframework.web.bind.annotation.ExceptionHandler({ AmazonClientException.class })
	public ResponseEntity<Object> amazonClientException(AmazonClientException e, WebRequest request) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Trata exceções AMAZON - 3.
	// --------------------------------
	@org.springframework.web.bind.annotation.ExceptionHandler({ AmazonS3Exception.class })
	public ResponseEntity<Object> amazonS3Exception(AmazonS3Exception e, WebRequest request) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
