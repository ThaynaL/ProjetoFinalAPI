package org.serratec.backend.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.serratec.backend.entity.StatusPedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PedidoRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idCliente;

    @NotEmpty(message = "A lista de itens não pode estar vazia!")
    private List<@Valid ItemRequestDTO> itens;

    @NotNull(message = "A data do pedido é obrigatória!")
    private LocalDateTime dataPedido;

    @NotNull(message = "A data de entrega é obrigatória!")
    private LocalDateTime dataEntregaPedido;

    @NotNull(message = "O status do pedido é obrigatório!")
    private StatusPedido statusPedido;

    // Getters e setters

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<ItemRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemRequestDTO> itens) {
        this.itens = itens;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getDataEntregaPedido() {
        return dataEntregaPedido;
    }

    public void setDataEntregaPedido(LocalDateTime dataEntregaPedido) {
        this.dataEntregaPedido = dataEntregaPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }
}
