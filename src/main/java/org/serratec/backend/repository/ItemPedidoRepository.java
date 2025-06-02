package org.serratec.backend.repository;
import org.serratec.backend.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

//alteramos o nome da classe de venda para ItemPedido

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
