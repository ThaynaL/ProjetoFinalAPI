package org.serratec.backend.controller;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Pagina para trabalhar com endpoints
 * pedidos
 */

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;
    @GetMapping({"id"})
    public ResponseEntity<PedidoResponseDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(service.listarPedidos());
    }
}



