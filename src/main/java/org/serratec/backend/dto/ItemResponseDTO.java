package org.serratec.backend.dto;
import org.serratec.backend.entity.ItemPedido;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.Produto;

import java.math.BigDecimal;


public class ItemResponseDTO {
    private ProdutoResponseDTO produtoResponseDTO;
    private PedidoResponseDTO pedidoResponseDTO;
    private Integer quantidade;
    private BigDecimal valorTotal;
    private BigDecimal valorUnitario;
    private Double descontoPercentual;

    public ItemResponseDTO() {
    }

    public ItemResponseDTO(ItemPedido itemPedido) {
        //this.produtoResponseDTO = itemPedido.getId().getProduto().toProdutoResponseDTO();
        //this.pedidoResponseDTO = itemPedido.getId().getPedido().toPedidoResponseDTO();
        this.quantidade = itemPedido.getQuantidade();
        this.valorUnitario = itemPedido.getValorUnitario();
        this.descontoPercentual = itemPedido.getDescontoPercentual();
    }

    public ProdutoResponseDTO getProdutoResponseDTO() {
        return produtoResponseDTO;
    }

    public void setProdutoResponseDTO(ProdutoResponseDTO produtoResponseDTO) {
        this.produtoResponseDTO = produtoResponseDTO;
    }

    public PedidoResponseDTO getPedidoResponseDTO() {
        return pedidoResponseDTO;
    }

    public void setPedidoResponseDTO(PedidoResponseDTO pedidoResponseDTO) {
        this.pedidoResponseDTO = pedidoResponseDTO;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
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
