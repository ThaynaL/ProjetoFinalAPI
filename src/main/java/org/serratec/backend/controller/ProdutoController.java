package org.serratec.backend.controller;
import jakarta.validation.Valid;
import org.eclipse.angus.mail.imap.protocol.ID;
import org.serratec.backend.dto.ProdutoDTO;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.repository.ProdutoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Todos os imports usados
 * acima
 */

@RestController
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    /**
     * PostMapping /products
     * Enviando produtos para o banco de dados
     */

    @PostMapping("/produtos")
    public ResponseEntity<Produto> saveProduct(@RequestBody @Valid ProdutoDTO produtoDTO) {
        var produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoRepository.save(produto));
    }

    /**
     * GetMapping /products
     * Buscando os produtos
     * em banco de dados
     */

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> getAllProducts(){
        List<Produto> productsList = ProdutoRepository.findAll();
        if(!productsList.isEmpty()){
            for(Produto produto : produtoList){
                ID id = produto.getId();
                produto.add(linkTo(methodOn(ProdutoController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    /**
     * @GetMapping /products/{id}
     * Buscar produto em banco
     * que seja por ID
     */

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> getOneProduto(@PathVariable(value="id") ID id){
        Optional<Produto> produtoObj = produtoRepository.findById(id);
        if (produtoObj.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao foi encontrado.");
        }
        produtoObj.get().add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(produtoObj);
    }

    /**
     * Atualizacao do produto
     * em banco, buscando
     * pelo ID
     */

    @PutMapping({"/produtos/{id}"})
    public ResponseEntity<Produto> updateProduto(@PathVariable(value="id") ID id,
                                                @RequestBody @Valid ProdutoDTO produtoDTO){
        Optional<Produto> produtoObj = produtoRepository.findById(id);
        if (produtoObj.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao foi encontrado.");
        }
        var productModel = produtoObj.get();
        BeanUtils.copyProperties(produtoDTO, "id");
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(productModel));
    }

    /**
     * Deletando produto
     * em banco, buscando pelo ID
     */

    @DeleteMapping({"/products/{id}"})
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id){
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

}
