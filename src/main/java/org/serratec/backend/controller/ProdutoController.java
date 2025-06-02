package org.serratec.backend.controller;
import jakarta.validation.Valid;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.dto.ProdutoResponseDTO;
import org.serratec.backend.service.PedidoService;
import org.serratec.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    /**
     * A funcao @beanutils.copyproperties
     * vai copiar os dados do pedidoDTO para o pedido
     */

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodosProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return produtoService.listarProdutos(id);
    }

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        return new ResponseEntity<>(produtoService.cadastrarProduto(produtoDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/produtos/{id}"})
    public ResponseEntity<PedidoResponseDTO> atualizarProduto(@PathVariable(value="id") UUID id,
                                                             @RequestBody @Valid ProdutoRequestDTO produtoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizarProduto(id, produtoDTO));
    }


    @DeleteMapping({"/produtos/{id}"})
    public ResponseEntity<Object> deletarProduto(@PathVariable(value="id") UUID id){
        produtoService.deletarProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso. ");
    }


}
