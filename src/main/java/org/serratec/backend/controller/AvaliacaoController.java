package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.AvaliacaoDTO;
import org.serratec.backend.entity.Avaliacao;
import org.serratec.backend.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<Avaliacao> criar(@RequestBody AvaliacaoDTO dto) throws JsonProcessingException {
    	Avaliacao teste = new Avaliacao() ;
    	System.out.println("DTO recebido: " + new ObjectMapper().writeValueAsString(dto));


    	return ResponseEntity.status(HttpStatus.CREATED).body(teste);
    
    	//return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.criarAvaliacao(dto));
   
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Avaliacao>> listarPorProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(avaliacaoService.listarPorProduto(produtoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizar(@PathVariable Long id, @RequestBody AvaliacaoDTO dto) {
        return ResponseEntity.ok(avaliacaoService.atualizarAvaliacao(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        avaliacaoService.deletarAvaliacao(id);
        return ResponseEntity.noContent().build();
    }
}

