package org.serratec.backend.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idCliente;

    @NotEmpty(message = "A lista nao pode estar vazia!")
    private List<@Valid ItemDTO> itens;

    private LocalDateTime dataPedido; // ← Adiciona aqui

    public PedidoRequestDTO() {
    }

    public PedidoRequestDTO(Long idCliente, List<ItemDTO> itens, LocalDateTime dataPedido) {
        this.idCliente = idCliente;
        this.itens = itens;
        this.dataPedido = dataPedido;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<ItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemDTO> itens) {
        this.itens = itens;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }
}