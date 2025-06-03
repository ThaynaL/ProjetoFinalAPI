package org.serratec.backend.controller;
import jakarta.validation.Valid;
import org.serratec.backend.dto.ProdutoRequestDTO;
import org.serratec.backend.dto.ProdutoResponseDTO;
import org.serratec.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodosProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        return new ResponseEntity<>(service.cadastrarProduto(produtoRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable(value="id") Long id,
                                                             @RequestBody @Valid ProdutoRequestDTO produtoDTO){
        return ResponseEntity.status(HttpStatus.OK).body
                (service.atualizarProduto(id, produtoDTO));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Object> deletarProduto(@PathVariable(value="id") Long id){
        service.deletarProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso. ");
    }


}
