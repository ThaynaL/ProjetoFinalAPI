package org.serratec.backend.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemDTO {
    @NotNull(message = "O id do produto é obrigatório!")
    private Long id;

    @NotNull(message = "A quantidade do produto é obrigatória!")
    @Positive(message = "A quantidade deve ser maior que zero!")
    private Long quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
