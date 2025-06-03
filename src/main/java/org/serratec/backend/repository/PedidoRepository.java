package org.serratec.backend.repository;
import org.serratec.backend.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> id(Long id);
    /**
    boolean findById(UUID id);
    void deleteById(UUID id);
     */
}
