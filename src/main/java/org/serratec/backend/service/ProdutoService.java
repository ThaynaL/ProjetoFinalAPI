package org.serratec.backend.service;
import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.dto.ProdutoResponseDTO;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto){
        Produto produto = new Produto();
        produto.setNomeProduto(dto.getNomeProduto());
        produto.setDescricaoProduto(dto.getDescricaoProduto());
        produto.setValorProduto(dto.getValorProduto());
        produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> listarProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> produtosDTO =new ArrayList<>();
        for (Produto produto : produtos) {
            produtosDTO.add(new ProdutoResponseDTO(produto));
        }
        return produtosDTO;
    }


    public ProdutoResponseDTO buscarPorId(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            Produto pdt = produto.get();
            return new ProdutoResponseDTO(pdt);
        }else{
            throw new RuntimeException("Produto informado nao foi encontrado!");
        }
    }

    /**
     * Atualizar produto
     * por ID
     */

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO dto){
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Produto informado nao foi encontrado!"));
        produtoExistente.setNomeProduto(dto.getNomeProduto());
        produtoExistente.setDescricaoProduto(dto.getDescricaoProduto());
        produtoExistente.setValorProduto(dto.getValorProduto());
        produtoRepository.save(produtoExistente);
        return new ProdutoResponseDTO(produtoExistente);
    }
    /**
     * Deletor produto
     * por ID
     */

    public void deletarProduto(Long id){
        if(!produtoRepository.existsById(id)){
            throw new RuntimeException("Produto informado nao foi encontrado!");
        }
        produtoRepository.deleteById(id);
    }

}
