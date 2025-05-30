package org.serratec.backend.dto;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProdutoDTO {
    @NotNull
    private Long id;
    @NotNull
    private String nomeProduto;
    @NotNull
    private String descricaoProduto;
    @NotNull
    private BigDecimal valorProduto;

    /**
     * Construtor vazio
     */

    public ProdutoDTO(){
    }

    public ProdutoDTO(Long id, String nomeProduto, String descricaoProduto, BigDecimal valorProduto) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.valorProduto = valorProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
