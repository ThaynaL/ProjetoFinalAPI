package org.serratec.backend.repository;
import org.serratec.backend.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Override
    Optional<Produto> findById(Long id);
}
