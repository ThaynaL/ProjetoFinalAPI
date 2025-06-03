package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.CategoriaRequestDTO;
import org.serratec.backend.dto.CategoriaResponseDTO;
import org.serratec.backend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
    
    @Operation(summary = "Insere uma nova categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public CategoriaResponseDTO inserir(@RequestBody @Valid CategoriaRequestDTO dto) {
        return service.inserir(dto);
    }
    
    @Operation(summary = "Atualiza uma categoria existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @PutMapping("/{id}")
    public CategoriaResponseDTO editar(@PathVariable Long id, @RequestBody @Valid CategoriaRequestDTO dto) {
        return service.editar(id, dto);
    }
    
    @Operation(summary = "Deleta uma categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
