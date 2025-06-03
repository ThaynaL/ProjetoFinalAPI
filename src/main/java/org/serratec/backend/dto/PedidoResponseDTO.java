package org.serratec.backend.dto;
import org.serratec.backend.entity.ItemPedido;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.StatusPedido;
import java.time.LocalDateTime;
import java.util.List;


public class PedidoResponseDTO {
    private Long id;
    private LocalDateTime dataPedido;
    private StatusPedido statusPedido;
    private LocalDateTime dataEntregaPedido;
    private List<ItemPedido> itemPedidos;

   public PedidoResponseDTO(Pedido pedido) {
	   this.id = pedido.getId();
	   this.dataPedido = pedido.getDataPedido();
	   this.statusPedido = pedido.getStatusPedido();
	   this.dataEntregaPedido = pedido.getDataEntregaPedido();
	   this.itemPedidos = pedido.getItemPedidos();
   }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public LocalDateTime getDataEntregaPedido() {
        return dataEntregaPedido;
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }
}
