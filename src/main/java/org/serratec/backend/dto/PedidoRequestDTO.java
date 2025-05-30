package org.serratec.backend.dto;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


public class PedidoRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    private List<ItemDTO> itens;

    public List<ItemDTO> getItens() {
        return itens;
    }
    public void setItens(List<ItemDTO> itens) {
        this.itens = itens;
    }

}
