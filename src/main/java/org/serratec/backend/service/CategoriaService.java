package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.dto.CategoriaRequestDTO;
import org.serratec.backend.dto.CategoriaResponseDTO;
import org.serratec.backend.entity.Categoria;
import org.serratec.backend.exception.CategoriaException;
import org.serratec.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    
    public List<CategoriaResponseDTO> listar() {
        List<Categoria> categorias = repository.findAll();
        List<CategoriaResponseDTO> categoriasDTO = new ArrayList<>();

        for (Categoria categoria : categorias) {
            categoriasDTO.add(new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNomeCategoria(),
                categoria.getDescricaoCategoria()
            ));
        }
        return categoriasDTO;
    }

    
    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria categoria = repository.findById(id)
            .orElseThrow(() -> new CategoriaException("Categoria com id " + id + " não encontrada!"));
        
        return new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNomeCategoria(),
            categoria.getDescricaoCategoria()
        );
    }

    @Transactional
    public CategoriaResponseDTO inserir(CategoriaRequestDTO dto) {
        Optional<Categoria> existente = repository.findByNomeCategoria(dto.getNomeCategoria());
        if (existente.isPresent()) {
            throw new CategoriaException("Já existe uma categoria com o nome '" + dto.getNomeCategoria() + "'!");
        }

        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(dto.getNomeCategoria());
        categoria.setDescricaoCategoria(dto.getDescricaoCategoria());

        Categoria salva = repository.save(categoria);

        return new CategoriaResponseDTO(
            salva.getId(),
            salva.getNomeCategoria(),
            salva.getDescricaoCategoria()
        );
    }

    @Transactional
    public CategoriaResponseDTO editar(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = repository.findById(id)
            .orElseThrow(() -> new CategoriaException("categoria com id " + id + " não encontrada!"));

        categoria.setNomeCategoria(dto.getNomeCategoria());
        categoria.setDescricaoCategoria(dto.getDescricaoCategoria());

        Categoria atualizada = repository.save(categoria);

        return new CategoriaResponseDTO(
            atualizada.getId(),
            atualizada.getNomeCategoria(),
            atualizada.getDescricaoCategoria()
        );
    }

    
    @Transactional
    public void deletar(Long id) {
       Categoria categoria = repository.findById(id)
            .orElseThrow(() -> new CategoriaException("Categoria com id " + id + " não encontrada"));
        
        repository.delete(categoria);
    }
}
