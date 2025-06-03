package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.dto.ClienteRequestDTO;
import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;
    
    @Operation(summary = "Lista todos os clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clientes listados com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listar());
    }
    
    @Operation(summary = "Cadastra um novo cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para o cadastro")
    })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO>  cadastrar(@RequestBody @Valid ClienteRequestDTO cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(cliente));
    }
    
    @Operation(summary = "Atualiza um cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable UUID id, @RequestBody @Valid ClienteRequestDTO cliente) {
        cliente.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, cliente));
    }
    
    @Operation(summary = "Remove um cliente pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable UUID id) {
        service.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        service.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
