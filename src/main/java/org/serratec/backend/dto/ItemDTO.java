package org.serratec.backend.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * Classe ItemDTO
 * foi criada para
 * poder passar os atributos
 * idProduto e quantidade
 * para o pedido
 */

public class ItemDTO {
    @NotNull(message = "O id do produto é obrigatório!")
    private Long idProduto;

    @NotNull(message = "A quantidade do produto é obrigatória!")
    @Positive(message = "A quantidade deve ser maior que zero!")
    private Integer quantidade;

    @NotNull(message = "O valor unitário do produto é obrigatório!")
    @Positive(message = "O valor unitário deve ser maior que zero!")
    private BigDecimal valorUnitario;

    public Long getId() {
        return idProduto;
    }

    public void setId(Long id) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
