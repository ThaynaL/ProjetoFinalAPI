package org.serratec.backend.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campo nome deve ser preenchido!")
    @Size(min = 2, max = 100, message  = "O campo nome deve ter entre 2 e 100 caracteres.")
    private String nomeProduto;

    @NotBlank(message = "O campo descricao deve ser preenchido!")
    @Size(max = 255, message = "A descricao deve ter no maximo 255 caracteres!")
    private String descricaoProduto;

    @NotNull(message = "O campo valor deve ser preenchido!")
    @Positive(message = "O campo valor deve ser maior que zero!")
    private BigDecimal valorProduto ;

    @NotNull(message = "O campo idCategoria deve ser preenchido!")
    private Long idCategoria;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public ProdutoRequestDTO(){
    }

    /**
     * Construtor para
     * UsuarioRequestDTO.
     */

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
}