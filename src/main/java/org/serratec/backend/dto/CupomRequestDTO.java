package org.serratec.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CupomRequestDTO {

    @NotBlank(message = "O código do cupom não pode estar em branco.")
    private String codigo;

    @NotNull(message = "O valor de desconto é obrigatório.")
    private Double percentualDesconto;

    @NotNull(message = "A data de validade é obrigatória.")
    @FutureOrPresent(message = "A data de validade deve ser hoje ou uma data futura.")
    private LocalDate dataValidade;

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(Double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}
