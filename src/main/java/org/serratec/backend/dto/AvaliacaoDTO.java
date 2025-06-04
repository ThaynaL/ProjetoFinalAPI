package org.serratec.backend.dto;

import java.util.UUID;

public class AvaliacaoDTO {

    private int nota;
    private String comentario;
    private Long produtoId;
    private UUID clienteId;
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	public UUID getClienteId() {
		return clienteId;
	}
	public void setClienteId(UUID clienteId) {
		this.clienteId = clienteId;
	}

    
}

