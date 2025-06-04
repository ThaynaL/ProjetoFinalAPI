package org.serratec.backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Nome do produto", example = "Mouse Gamer RGB")
    @NotBlank(message = "O campo nome deve ser preenchido!")
    @Size(min = 2, max = 100, message = "O campo nome deve ter entre 2 e 100 caracteres.")
    private String nomeProduto;

    @Schema(description = "Descrição do produto", example = "Mouse com sensor óptico de alta precisão e iluminação RGB")
    @NotBlank(message = "O campo descricao deve ser preenchido!")
    @Size(max = 255, message = "A descricao deve ter no maximo 255 caracteres!")
    private String descricaoProduto;

    @Schema(description = "Valor do produto em reais", example = "149.90")
    @NotNull(message = "O campo valor deve ser preenchido!")
    @Positive(message = "O campo valor deve ser maior que zero!")
    private BigDecimal valorProduto;

    @Schema(description = "ID da categoria à qual o produto pertence", example = "2")
    @NotNull(message = "O campo idCategoria deve ser preenchido!")
    private Long idCategoria;

    public ProdutoRequestDTO() {}

    public ProdutoRequestDTO(String nomeProduto, String descricaoProduto, BigDecimal valorProduto, Long idCategoria) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.valorProduto = valorProduto;
        this.idCategoria = idCategoria;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(BigDecimal valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
