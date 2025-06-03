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
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;


    //get
    @GetMapping
    public List<PedidoResponseDTO> listarPedidos(){
        return service.listarPedidosPorCliente();
    }


    //get pagina
    @GetMapping("/pagina")
    public Page<PedidoResponseDTO> listarPorPagina(@PageableDefault(page = 1, size = 10, sort = {"id", "valor"},
            direction = Sort.Direction.ASC) Pageable pageable){
        return service.listarPorPagina(pageable);
    }


    //get id
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }


    //post
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody @Valid PedidoRequestDTO pedidoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPedido(pedidoDTO));
    }


    //put id
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizarPedido(@PathVariable(value="id") Long id,
                                                             @RequestBody @Valid PedidoRequestDTO pedidoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPedido(id, pedidoDTO));
    }


    //delete id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPedido(@PathVariable(value="id") Long id){
        service.deletarPedido(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deletedo com sucesso.");
    }

}
