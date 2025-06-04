package org.serratec.backend.controller;

import org.serratec.backend.service.EmailAniversarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailAniversarioService emailAniversarioService;

    @PostMapping
    public ResponseEntity<String> enviarEmailAniversario() {
        emailAniversarioService.enviarEmailAniversario();
        return ResponseEntity.ok("Email enviado com sucesso!");
    }
}
