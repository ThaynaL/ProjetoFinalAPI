package org.serratec.backend.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.ClientePerfil;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class ClienteRequestDTO {
    private UUID id;
    @NotBlank(message = "Campo obrigatório")
    private String nome;
    @Pattern(regexp = "\\d{2} ?\\d{4,5}-?\\d{4}", message = "Telefone inválido")
    @NotBlank(message = "Campo obrigatório")
    private String telefone;
    @Email(message = "E-mail inválido")
    @NotBlank(message = "Campo obrigatório")
    private String email;
    @NotBlank(message = "Campo obrigatório")
    private String senha;
    @CPF(message = "CPF inválido")
    @NotBlank(message = "Campo obrigatório")
    private String cpf;
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
    private String cep;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public ClienteRequestDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
        this.senha = cliente.getSenha();
        this.cpf = cliente.getCpf();
        this.cep = cliente.getEndereco().getCep();
    }

    public ClienteRequestDTO() {
    }
    private Set<ClientePerfil> clientePerfis = new HashSet<>();

	public Set<ClientePerfil> getClientePerfis() {
		return clientePerfis;
	}

	public void setClientePerfis(Set<ClientePerfil> clientePerfis) {
		this.clientePerfis = clientePerfis;
	}
}
