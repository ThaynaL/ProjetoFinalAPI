package org.serratec.backend.service;
import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Cadastrando produto no sistema
     */

    public Produto cadastrarProduto(ProdutoRequestDTO dto){
        Produto produto = new Produto();
        produto.setNomeProduto(dto.getNomeProduto());
        produto.setDescricaoProduto(dto.getDescricaoProduto());
        produto.setValorProduto(dto.getValorProduto());
        return produtoRepository.save(produto);
    }

    /**
     * Listar todos os produtos
     */

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    /**
     * Busca o produto por id
     */

    public Optional<Produto> buscarPorId(Long id){
        return produtoRepository.findById(id);
    }

    /**
     * Atualizar produto
     * por ID
     */

    public Produto atualizarProduto(Long id, ProdutoRequestDTO dto){
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Produto informado nao foi encontrado!"));
        produtoExistente.setNomeProduto(dto.getNomeProduto());
        produtoExistente.setDescricaoProduto(dto.getDescricaoProduto());
        produtoExistente.setValorProduto(dto.getValorProduto());
        return produtoRepository.save(produtoExistente);
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
