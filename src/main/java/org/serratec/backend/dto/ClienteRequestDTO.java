package org.serratec.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;
import org.serratec.backend.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


public class ClienteRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(
            description = "Identificador único do cliente. É gerado automaticamente.",
            example = "f47ac10b-58cc-4372-a567-0e02b2c3d479"
    )
    private UUID id;
    @Schema(
            description = "Nome completo do cliente.",
            example = "Juliana Périco"
    )@NotBlank(message = "Campo obrigatório")
    private String nome;
    @Schema(
            description = "Número de telefone do cliente no formato nacional.",
            example = "2498765-4321"
    )
    @Pattern(regexp = "\\d{2}\\d{4,5}-?\\d{4}", message = "Telefone inválido")
    @NotBlank(message = "Campo obrigatório")
    private String telefone;
    @Schema(
            description = "Endereço de e-mail do cliente.",
            example = "juliana.perico@email.com"
    )
    @Email(message = "E-mail inválido")
    @NotBlank(message = "Campo obrigatório")
    private String email;
    @Schema(
            description = "Senha de acesso do cliente.",
            example = "J123456789"
    )
    @NotBlank(message = "Campo obrigatório")
    private String senha;
    @Schema(
            description = "CPF do cliente. Deve conter apenas números.",
            example = "12345678909"
    )
    @CPF(message = "CPF inválido")
    @NotBlank(message = "Campo obrigatório")
    private String cpf;
    @Schema(
            description = "CEP do cliente. Deve estar no formato 00000-000 ou 00000000.",
            example = "25730-365"
    )
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
    private String cep;

    @Schema(
            description = "Data de nascimento do cliente. Deve estar no formato dd/MM/yyyy.",
            example = "02/08/2000"
    )
    @PastOrPresent(message = "A data de nascimento não pode estar no futuro.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

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
        this.dataNascimento = cliente.getDataNascimento();
    }

    public ClienteRequestDTO() {
    }
}
