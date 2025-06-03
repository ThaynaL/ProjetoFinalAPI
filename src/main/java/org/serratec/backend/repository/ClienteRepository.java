package org.serratec.backend.repository;

import org.serratec.backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    @Override
    Optional<Cliente> findById(UUID uuid);

    Optional<Cliente> findByEmail(String email);

    @Query("SELECT c FROM Cliente c WHERE function('month', c.dataNascimento) = :mes")
    List<Cliente> findByMesAniversario(@Param("mes") int mes);
}
