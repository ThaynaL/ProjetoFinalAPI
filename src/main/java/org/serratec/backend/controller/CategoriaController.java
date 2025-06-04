package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.CategoriaRequestDTO;
import org.serratec.backend.dto.CategoriaResponseDTO;
import org.serratec.backend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")

public class CategoriaController {

    @Autowired
    private CategoriaService service;
    
    @Operation(summary = "Lista todas as categorias")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categorias listadas com sucesso")
    })
    @GetMapping
    public List<CategoriaResponseDTO> listar() {
        return service.listar();
    }
    
    @Operation(summary = "Busca uma categoria pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content())
    })
    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
    
    @Operation(summary = "Insere uma nova categoria")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content())
    })
    @PostMapping
    public CategoriaResponseDTO inserir(@RequestBody @Valid CategoriaRequestDTO dto) {
        return service.inserir(dto);
    }
    
    @Operation(summary = "Atualiza uma categoria existente")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content())
    })
    @PutMapping("/{id}")
    public CategoriaResponseDTO editar(@PathVariable Long id, @RequestBody @Valid CategoriaRequestDTO dto) {
        return service.editar(id, dto);
    }
    
    @Operation(summary = "Deleta uma categoria")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content())
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
