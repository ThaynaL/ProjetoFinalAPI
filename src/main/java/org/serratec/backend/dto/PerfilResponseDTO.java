package org.serratec.backend.dto;

import org.serratec.backend.entity.Perfil;

import io.swagger.v3.oas.annotations.media.Schema;

public class PerfilResponseDTO {

    @Schema(description = "ID do perfil", example = "1")
    private Long id;

    @Schema(description = "Nome do perfil", example = "ROLE_CLIENTE")
    private String nome;

    public PerfilResponseDTO() {
    }

    public PerfilResponseDTO(Perfil perfil) {
        this.id = perfil.getId();
        this.nome = perfil.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
