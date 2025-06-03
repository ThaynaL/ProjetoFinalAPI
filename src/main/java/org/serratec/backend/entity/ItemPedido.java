package org.serratec.backend.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.serratec.backend.entity.pk.PedidoProdutoPk;

@Entity
public class ItemPedido {

    @EmbeddedId
    private PedidoProdutoPk id = new PedidoProdutoPk();

    private Double descontoPercentual;
    private Integer quantidade;
    private BigDecimal valorUnitario;

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, Pedido pedido,
                      Double descontoPercentual, Integer quantidade,
                      BigDecimal valorUnitario) {
        id.setProduto(produto);
        id.setPedido(pedido);
        this.descontoPercentual = descontoPercentual;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public PedidoProdutoPk getId() {
        return id;
    }

    public void setId(PedidoProdutoPk id) {
        this.id = id;
    }

    public Double getDescontoPercentual() {
        return descontoPercentual;
    }

    public void setDescontoPercentual(Double descontoPercentual) {
        this.descontoPercentual = descontoPercentual;
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
}
