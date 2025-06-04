package org.serratec.backend.dto;

import java.math.BigDecimal;

import org.serratec.backend.entity.Produto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProdutoResponseDTO {

    @Schema(description = "ID do produto", example = "1")
    private Long id;

    @Schema(description = "Nome do produto", example = "Mouse Gamer RGB")
    private String nomeProduto;

    @Schema(description = "Descrição do produto", example = "Mouse com sensor óptico de alta precisão e iluminação RGB")
    private String descricaoProduto;

    @Schema(description = "Valor do produto", example = "149.90")
    private BigDecimal valorProduto;

    @Schema(description = "ID da categoria", example = "2")
    private Long idCategoria;

    @Schema(description = "Nome da categoria", example = "Informática")
    private String nomeCategoria;

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.nomeProduto = produto.getNomeProduto();
        this.descricaoProduto = produto.getDescricaoProduto();
        this.valorProduto = produto.getValorProduto();

        if (produto.getCategoria() != null) {
            this.idCategoria = produto.getCategoria().getId();
            this.nomeCategoria = produto.getCategoria().getNomeCategoria();
        }
    }

    public Long getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public void setValorProduto(BigDecimal valorProduto) {
        this.valorProduto = valorProduto;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
