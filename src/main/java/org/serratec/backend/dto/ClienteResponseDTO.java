package org.serratec.backend.dto;

import org.serratec.backend.entity.Cliente;

import java.time.LocalDate;
import java.util.UUID;

public class ClienteResponseDTO {
    private UUID id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;

    public Integer getMesAniversario() {
        return (dataNascimento !=null) ? dataNascimento.getMonthValue() : null;
    }

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getIdUuid();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
