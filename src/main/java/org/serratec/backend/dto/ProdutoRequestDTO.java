package org.serratec.backend.dto;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO {
    @NotBlank(message = "O campo nome deve ser preenchido!")
    @Size(min = 2, max = 100, message  = "O campo nome deve ter entre 2 e 100 caracteres.")
    private String nomeProduto;

    @NotBlank(message = "O campo descricao deve ser preenchido!")
    private String descricaoProduto;

    @NotNull(message = "O campo valor deve ser preenchido!")
    private BigDecimal valorProduto ;


    public ProdutoRequestDTO(){
    }

    /**
     * Construtor para
     * UsuarioRequestDTO.
     */

    public ProdutoRequestDTO(String nomeProduto, String descricaoProduto, BigDecimal valorProduto) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.valorProduto = valorProduto;
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