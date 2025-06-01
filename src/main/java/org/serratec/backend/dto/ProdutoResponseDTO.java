package org.serratec.backend.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class ProdutoResponseDTO {
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 2, max = 100, message = "O nome do produto deve ter entre 2 e 100 caracteres.")
    private String nomeProduto;

    @NotNull
    @Size(min=2, max = 255, message = "A descricao do produto nao pode passar 255 caracteres!")
    private String descricaoProduto;

    @NotNull
    private BigDecimal valorProduto;

    /**
     * Construtor vazio
     * e construtor cheio com
     * os atributos
     */

    public ProdutoResponseDTO(){
    }

    public ProdutoResponseDTO(Long id, String nomeProduto, String descricaoProduto, BigDecimal valorProduto) {
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
