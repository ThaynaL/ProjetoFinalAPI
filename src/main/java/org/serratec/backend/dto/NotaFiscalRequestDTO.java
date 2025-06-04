package org.serratec.backend.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class NotaFiscalRequestDTO {

    @Schema(description = "Nome completo do cliente", example = "Joana Silva")
    private String nomeCliente;

    @Schema(description = "CPF do cliente", example = "16434597754")
    private String cpfCliente;

    @Schema(description = "Forma de pagamento escolhida", example = "Cartão de Crédito")
    private String formaPagamento;

    @Schema(description = "Valor total da nota fiscal", example = "259.90")
    private Double valorTotal;

    @Schema(description = "Lista de nomes dos itens comprados", example = "[\"Teclado Mecânico\", \"Mouse Gamer\"]")
    private List<String> itens;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<String> getItens() {
        return itens;
    }

    public void setItens(List<String> itens) {
        this.itens = itens;
    }
}
