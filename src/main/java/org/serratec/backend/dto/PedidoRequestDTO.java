package org.serratec.backend.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


public class PedidoRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "A lista nao pode estar vazia!")
    private List<@Valid ItemDTO> itens;

    public List<ItemDTO> getItens() {
        return itens;
    }
    public void setItens(List<ItemDTO> itens) {
        this.itens = itens;
    }

}
