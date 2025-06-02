package org.serratec.backend.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


public class PedidoRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "O id do cliente é obrigatório!")
    private Long idCliente;

    @NotEmpty(message = "A lista nao pode estar vazia!")
    private List<@Valid ItemDTO> itens;

    public PedidoRequestDTO() {

    }

    public PedidoRequestDTO(Long idCliente, List<ItemDTO> itens) {
        this.idCliente = idCliente;
        this.itens = itens;
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
}
