package org.serratec.backend.dto;
import org.serratec.backend.entity.ItemPedido;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.StatusPedido;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação dos dados de retorno de um pedido")
public class PedidoResponseDTO {

     @Schema(description = "Identificador único do pedido", example = "1")
    private Long id;
     @Schema(description = "Data e hora em que o pedido foi realizado", example = "2025-06-03T14:30:00")
    private LocalDateTime dataPedido;
     @Schema(description = "Status atual do pedido", example = "EM_PROCESSAMENTO")
    private StatusPedido statusPedido;
     @Schema(description = "Data e hora prevista para entrega do pedido", example = "2025-06-10T18:00:00")
    private LocalDateTime dataEntregaPedido;
     @Schema(description = "Lista de itens que compõem o pedido")
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