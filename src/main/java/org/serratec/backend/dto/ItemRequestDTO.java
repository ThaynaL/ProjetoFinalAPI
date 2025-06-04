package org.serratec.backend.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ItemRequestDTO {
	 @Schema(description = "ID do produto", example = "2")
	    @NotNull
	    private Long idProduto;

	    @Schema(description = "Quantidade do produto", example = "2")
	    @NotNull
	    private Integer quantidade;

	    @Schema(description = "Valor unitário", example = "120.50")
	    @NotNull
	    private BigDecimal valorUnitario;

	    @Schema(description = "Desconto percentual", example = "0.05")
	    private Double descontoPercentual;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getDescontoPercentual() {
        return descontoPercentual;
    }

    public void setDescontoPercentual(Double descontoPercentual) {
        this.descontoPercentual = descontoPercentual;
    }
}
