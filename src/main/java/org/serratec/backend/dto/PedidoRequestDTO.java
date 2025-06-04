package org.serratec.backend.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.serratec.backend.entity.StatusPedido;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PedidoRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Id do Cliente que está realizando o Pedido", example = "1")
    @NotNull
    private Long idCliente;
    @Schema(description = "Lista de itens do Pedido deve conter pelo menos 1 item")
    @NotEmpty(message = "A lista de itens não pode estar vazia!")
    private List<@Valid ItemRequestDTO> itens;
    @Schema(description = "A data e hora que o Pedido foi realizado (ISO8601)", example = "2025-06-30T09:23:08")
    @NotNull(message = "A data do pedido é obrigatória!")
    private LocalDateTime dataPedido;
    @Schema(description = "A data de entrega do Pedido : (ISO8601)", example = "2025-07-15T10:30:00")
    @NotNull(message = "A data de entrega é obrigatória!")
    private LocalDateTime dataEntregaPedido;
    @Schema(description = "O Status atual do pedido :", example = "Aguardando pagamento")
    @NotNull(message = "O status do pedido é obrigatório!")
    private StatusPedido statusPedido;


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
