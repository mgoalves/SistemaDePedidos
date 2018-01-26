package com.alves.model;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.alves.model.enums.Perfil;
import com.alves.model.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
 
 
@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    //Atributos ----------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
 
    @NotBlank(message = "Não pode ser nulo ou vazio.")
	@Length(min = 2, max = 40, message = "Tamanho inválido.")
    @Column(length = 40, nullable = false)
    private String nome;
    
    @Email(message = "Email Inválido.") @Length(min = 5, max = 40, message = "Tamanho inválido.")
    @NotBlank(message = "Preenchimento obrigatório.")
    @Column(unique = true, length = 40, nullable = false)
    private String email;
    
    @NotBlank(message = "Não pode ser nulo ou vazio.")
	@Length(min = 9, max = 14, message = "Tamanho inválido.")
    @Column(length = 14, name = "cpf_cnpj", unique = true, nullable = false)
    private String cpfOuCnpj;
    
    private Integer tipo;
    
    @JsonIgnore
    @NotBlank(message = "Não pode ser nulo ou vazio.")
	@Length(min = 8, max = 60, message = "Tamanho inválido.")
    @Column(length = 60, nullable = false)
    private String senha;
    
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();
    
    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>();
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis")
    private Set<Integer> perfis = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos =  new ArrayList<>();
    
	//Constructors -------------------------------
    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo == null) ? null : tipo.getId();
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }
    public Cliente() {
    	addPerfil(Perfil.CLIENTE);
    }
    
    //Getters and Setters --------------------------
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }
    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }
 
    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }
    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getId();
    }
    
    public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
    
    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    public Set<String> getTelefones() {
        return telefones;
    }
    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }
    
    public Set<Perfil> getPerfis(){
    	return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }
    public void addPerfil(Perfil perfil){
    	perfis.add(perfil.getId());
    }
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    //HashCode and Equals: ID, Email, CPF-CNPJ -----------------------------------
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpfOuCnpj == null) ? 0 : cpfOuCnpj.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (cpfOuCnpj == null) {
            if (other.cpfOuCnpj != null)
                return false;
        } else if (!cpfOuCnpj.equals(other.cpfOuCnpj))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}