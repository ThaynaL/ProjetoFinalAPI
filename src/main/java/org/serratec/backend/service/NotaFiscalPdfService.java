package org.serratec.backend.service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

import org.serratec.backend.dto.NotaFiscalRequestDTO;
import org.springframework.stereotype.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@Service
public class NotaFiscalPdfService {

    public byte[] gerarNotaFiscalPdf(NotaFiscalRequestDTO dto) throws DocumentException {
        Document documento = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance(documento, outputStream);
        documento.open();

        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Paragraph titulo = new Paragraph("Nota Fiscal - Loja Serratec", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);

        documento.add(new Paragraph(" "));

        documento.add(new Paragraph("CNPJ da Loja: 12.345.678/0001-99"));
        documento.add(new Paragraph("Data: " + LocalDate.now()));
        documento.add(new Paragraph("Cliente: " + dto.getNomeCliente()));
        documento.add(new Paragraph("CPF: " + dto.getCpfCliente()));
        documento.add(new Paragraph("Forma de Pagamento: " + dto.getFormaPagamento()));

        documento.add(new Paragraph(" "));

        PdfPTable tabela = new PdfPTable(1);
        tabela.addCell("Itens do Pedido:");

        for (String item : dto.getItens()) {
            tabela.addCell(item);
        }

        documento.add(tabela);

        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Valor Total: R$ " + String.format("%.2f", dto.getValorTotal())));

        documento.close();
        return outputStream.toByteArray();
    }
}