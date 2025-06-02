package org.serratec.backend.controller;
import jakarta.validation.Valid;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * A funcao @beanutils.copyproperties
     * vai copiar os dados do pedidoDTO para o pedido
     */

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodosPedidos(){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return pedidoService.listarPedidos(id);
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> criarPedido(@RequestBody @Valid PedidoRequestDTO pedidoDTO) {
       return new ResponseEntity<>(pedidoService.criarPedido(pedidoDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/pedidos/{id}"})
    public ResponseEntity<PedidoResponseDTO> atualizarPedido(@PathVariable(value="id") UUID id,
                                                             @RequestBody @Valid PedidoRequestDTO pedidoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.atualizarPedido(id, pedidoDTO));
    }


    @DeleteMapping({"/pedidos/{id}"})
    public ResponseEntity<Object> deletarPedido(@PathVariable(value="id") UUID id){
        pedidoService.deletarPedido(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deletedo com sucesso.");
    }


}
