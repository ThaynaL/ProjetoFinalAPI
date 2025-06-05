package org.serratec.backend.repository;

import java.util.Optional;

import org.serratec.backend.entity.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomRepository extends JpaRepository<Cupom, Long> {
    Optional<Cupom> findByCodigo(String codigo);
}
