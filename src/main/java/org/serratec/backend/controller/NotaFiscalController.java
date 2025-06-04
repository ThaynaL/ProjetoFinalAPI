package org.serratec.backend.controller;

import org.serratec.backend.dto.NotaFiscalRequestDTO;
import org.serratec.backend.service.NotaFiscalPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalPdfService pdfService;

    @PostMapping("/pdf")
    @Operation(summary = "Gerar PDF da nota fiscal",
               description = "Gera um arquivo PDF com os dados da nota fiscal enviados no corpo da requisição.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "PDF gerado com sucesso", content = @Content()),
        @ApiResponse(responseCode = "500", description = "Erro ao gerar o PDF", content = @Content())
    })
    public ResponseEntity<byte[]> gerarPdf(
        @RequestBody NotaFiscalRequestDTO dto) {

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
