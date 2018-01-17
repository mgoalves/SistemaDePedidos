package com.alves.model.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ClienteTelEndDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Atributos --------------------------------------------------
	private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	private String telefone1;
	private String telefone2;
	
	private Long cidadeId;
	
	//Construtor ----------------------------------------------------
	public ClienteTelEndDTO() {
	}

	
	//Getters and Setters  -------------------------------------------
	@NotBlank(message = "Não pode ser nulo ou vazio.")
	@Length(min = 2, max = 40, message = "Tamanho inválido.")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Email(message = "Email Inválido.") @Length(min = 5, max = 40, message = "Tamanho inválido.")
	@NotBlank(message = "Preenchimento obrigatório.")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	@NotBlank(message = "Não pode ser nulo ou vazio.")
	@Length(min = 9, max = 14, message = "Tamanho inválido.")
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	
	@NotBlank(message = "Preenchimento obrigatório.")
	@Length(min = 5, max = 40, message = "Tamanho inválido.")
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Length(min = 1, max = 5, message = "Tamanho inválido.")
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Length(max = 60, message = "Tamanho máximo: 60")
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@NotBlank(message = "Preenchimento obrigatório.")
	@Length(min = 5, max = 40, message = "Tamanho inválido.")
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotBlank(message = "Preenchimento obrigatório.")
	@Length(min = 8, max = 8, message = "Tamanho inválido.")
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

	@NotBlank(message = "Preenchimento obrigatório.")
	@Length(min = 8, max = 14, message = "Tamanho inválido.")
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	@Length(min = 8, max = 14, message = "Tamanho inválido.")
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public Long getCidadeId() {
		return cidadeId;
	}
	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}
}
