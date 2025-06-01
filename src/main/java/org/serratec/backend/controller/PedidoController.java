package org.serratec.backend.controller;
import jakarta.validation.Valid;
import org.eclipse.angus.mail.imap.protocol.ID;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.repository.PedidoRepository;
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
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    /**
     * PostMapping /pedidos
     * Enviando produtos
     * para o banco de dados
     */

    /**
     * A funcao @beanutils.copyproperties
     * vai copiar os dados do pedidoDTO para o pedido
     */


    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> savePedido(@RequestBody @Valid Pedido pedidoDTO) {
       Pedido pedidoSalvo = PedidoRepository.save(pedidoDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
    }

    /**
     * GetMapping /pedidos
     * Buscando os produtos
     * em banco de dados
     */


    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> getAllPedidos(){
        List<Pedido> pedidoLista = PedidoRepository.findAll();
        if(!pedidoLista.isEmpty()){
            for(Pedido pedido : pedidoLista){
                ID id = pedido.getId();
                pedido.add(linkTo(methodOn(ProdutoController.class)
                        .getOneProduto(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidoLista);
    }

    /**
     * @GetMapping /pedidos/{id}
     * Buscar pedido em banco
     * que seja por ID
     */

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<String> getOnePedido(@PathVariable(value="id") ID id){
        Optional<Pedido> pedidoPed = pedidoRepository.findById(id);
        if (pedidoPed.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O pedido informado nao é valido.");
        }
        pedidoPed.get().add(linkTo(methodOn(PedidoController.class).getOnePedido(id)).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(pedidoPed);
    }

    /**
     * Atualizacao do produto
     * em banco, buscando
     * pelo ID
     */

    @PutMapping({"/pedidos/{id}"})
    public ResponseEntity<String> updatePedido(@PathVariable(value="id") ID id,
                                                 @RequestBody @Valid PedidoRequestDTO pedidoRequestDTO){
        Optional<Pedido> pedidoPed = pedidoRepository.findById(id);
        if (pedidoPed.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O pedido informado nao foi encontrado.");
        }
        var produt = produtoObj.get();
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
