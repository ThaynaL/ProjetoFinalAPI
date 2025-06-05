package org.serratec.backend.dto;

import java.time.LocalDate;

import org.serratec.backend.entity.Cupom;

public class CupomResponseDTO {

    private Long id;
    private String codigo;
    private Double percentualDesconto;
    private LocalDate dataValidade;

    public CupomResponseDTO(Cupom cupom) {
        this.id = cupom.getId();
        this.codigo = cupom.getCodigo();
        this.percentualDesconto = cupom.getPercentualDesconto();
        this.dataValidade = cupom.getDataValidade();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Double getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }
}

