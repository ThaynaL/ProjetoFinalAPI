package org.serratec.backend.repository;
import org.serratec.backend.entity.Categoria;
import org.serratec.backend.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findById(Long id);
    List<Produto> findByNomeProduto(String nome);
    List<Produto> findByCategoria(Categoria categoria);
}
