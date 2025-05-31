package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.CategoriaRequestDTO;
import org.serratec.backend.dto.CategoriaResponseDTO;
import org.serratec.backend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public List<CategoriaResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public CategoriaResponseDTO inserir(@RequestBody @Valid CategoriaRequestDTO dto) {
        return service.inserir(dto);
    }

    @PutMapping("/{id}")
    public CategoriaResponseDTO editar(@PathVariable Long id, @RequestBody @Valid CategoriaRequestDTO dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
