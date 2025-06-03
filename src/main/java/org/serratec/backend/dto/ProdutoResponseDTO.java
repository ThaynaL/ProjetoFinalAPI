package org.serratec.backend.dto;

import org.serratec.backend.entity.Produto;

import java.math.BigDecimal;

public class ProdutoResponseDTO {
    private Long id;
    private String nomeProduto;
    private String descricaoProduto;
    private BigDecimal valorProduto;
    private Long idCategoria;
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




    public String getNomeCategoria() {
		return nomeCategoria;
	}




	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
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
}
