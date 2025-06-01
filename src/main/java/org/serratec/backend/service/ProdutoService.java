package org.serratec.backend.service;

import org.serratec.backend.entity.Produto;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    /**
     * Cia uma lista de produtos
     * que lista os produtos cadastrados
     */

    public List<Produto> listarProdutos(){
        List<Produto> produtos = repository.findAll();
        List<ProdutoResponseDTO> produtosDTO = new ArrayList<>();
    }

}
