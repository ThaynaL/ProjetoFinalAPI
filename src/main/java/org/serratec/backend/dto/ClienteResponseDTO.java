package org.serratec.backend.dto;

import org.serratec.backend.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

public class ClienteResponseDTO {
	
	@Schema(description = "Identificador único do cliente.", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
    private UUID id;
	@Schema(description = "Nome completo do cliente.", example = "Juliana Périco")
    private String nome;
	@Schema(description = "Número de telefone do cliente no formato nacional.", example = "24 98765-4321")
    private String telefone;
	@Schema(description = "Endereço de e-mail do cliente.", example = "juliana.perico@email.com")
    private String email;
	@Schema(description = "CPF do cliente (somente números).", example = "12345678909") 
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
