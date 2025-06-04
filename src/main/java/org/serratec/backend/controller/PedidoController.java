package org.serratec.backend.controller;
import jakarta.validation.Valid;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Operation(summary = "Listar todos os pedidos")
    @ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso")

    //get
    @GetMapping
    public List<PedidoResponseDTO> listarPedidos(){
        return service.listarPedidosPorCliente();
      
    }
    
    @Operation(summary = "Listar Pedidos Paginados")
    @ApiResponse(responseCode = "200", description = "Pedidos Paginados listados com sucesso")

    //get pagina
    @GetMapping("/pagina")
    public Page<PedidoResponseDTO> listarPorPagina(@PageableDefault(page = 1, size = 10, sort = {"id", "valor"},
            direction = Sort.Direction.ASC) Pageable pageable){
        return service.listarPorPagina(pageable);
    }

    @Operation(summary = "Buscar pedido por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    
    
    //get id
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }
    

    @Operation(summary = "Cadastrar um novo pedido")
    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso")
    
    //post
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody @Valid PedidoRequestDTO pedidoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPedido(pedidoDTO));
    }

    @Operation(summary = "Atualizar um pedido existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados Inválidos")
    })
    
    //put id
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizarPedido(@PathVariable(value="id") Long id,
                                                             @RequestBody @Valid PedidoRequestDTO pedidoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPedido(id, pedidoDTO));
    }

    
    @Operation(summary = "Deletar um pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    
    //delete id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPedido(@PathVariable(value="id") Long id){
        service.deletarPedido(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deletedo com sucesso.");
    }

}
