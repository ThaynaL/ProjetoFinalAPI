package org.serratec.backend.repository;

import org.serratec.backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    @Override
    Optional<Cliente> findById(UUID uuid);

    Optional<Cliente> findByEmail(String email);
}
