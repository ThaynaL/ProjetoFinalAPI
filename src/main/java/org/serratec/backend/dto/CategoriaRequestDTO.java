package org.serratec.backend.dto;

import org.serratec.backend.entity.Categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaRequestDTO {

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 2, max = 50, message = "O nome da categoria deve ter entre 2 e 50 caracteres.")
    private String nomeCategoria;
    
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
