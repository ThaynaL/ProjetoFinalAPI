package org.serratec.backend.dto;

import org.serratec.backend.entity.ItemPedido;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.Produto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class ItemResponseDTO {

    @Schema(description = "Informações do produto neste item do pedido")
    private ProdutoResponseDTO produtoResponseDTO;

    @Schema(description = "Resumo do pedido ao qual este item pertence")
    private PedidoResponseDTO pedidoResponseDTO;

    @Schema(description = "Quantidade de produtos neste item", example = "2")
    private Integer quantidade;

    @Schema(description = "Valor total calculado deste item", example = "359.98")
    private BigDecimal valorTotal;

    @Schema(description = "Valor unitário do produto", example = "199.99")
    private BigDecimal valorUnitario;

    @Schema(description = "Desconto percentual aplicado ao item (ex: 0.1 = 10%)", example = "0.1")
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
