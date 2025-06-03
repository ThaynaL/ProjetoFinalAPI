package org.serratec.backend.controller;

import org.serratec.backend.dto.NotaFiscalRequestDTO;
import org.serratec.backend.service.NotaFiscalPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalPdfService pdfService;

    @PostMapping("/pdf")
    public ResponseEntity<byte[]> gerarPdf(@RequestBody NotaFiscalRequestDTO dto) {
        try {
            byte[] pdfBytes = pdfService.gerarNotaFiscalPdf(dto);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=nota-fiscal.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
