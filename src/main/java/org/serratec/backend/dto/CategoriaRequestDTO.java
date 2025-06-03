package org.serratec.backend.dto;

import org.serratec.backend.entity.Categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;

public class CategoriaRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(
            description = "Nome da categoria. Deve ter entre 2 e 50 caracteres.",
            example = "Eletrônicos"
    )
    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 2, max = 50, message = "O nome da categoria deve ter entre 2 e 50 caracteres.")
    private String nomeCategoria;
    @Schema(
            description = "Descrição da categoria. Texto opcional com no máximo 255 caracteres.",
            example = "Produtos relacionados à tecnologia e aparelhos eletrônicos."
    )
    @Size(max = 255, message = "A descricao da categoria nao pode passar 255 caracteres!")
    private String descricaoCategoria;

    
    public CategoriaRequestDTO() {
    }

    public CategoriaRequestDTO(Categoria categoria) {
        this.nomeCategoria = categoria.getNomeCategoria();
        this.descricaoCategoria = categoria.getDescricaoCategoria();
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }
}
