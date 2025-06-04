package org.serratec.backend.entity;

import java.time.LocalDate;

import org.serratec.backend.entity.pk.ClientePerfilPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class ClientePerfil {

	@EmbeddedId
	private ClientePerfilPK id = new ClientePerfilPK();

	private LocalDate dataCriacao;

	public ClientePerfil() {
	}

	public ClientePerfil(Cliente cliente, Perfil perfil, LocalDate dataCriacao) {
		id.setCliente(cliente);
		id.setPerfil(perfil);
		this.dataCriacao = dataCriacao;
	}

	public void setCliente(Cliente cliente) {
		id.setCliente(cliente);
	}

	public Cliente getCliente() {
		return id.getCliente();
	}

	public void setPerfil(Perfil perfil) {
		id.setPerfil(perfil);
	}

	public Perfil getPerfil() {
		return id.getPerfil();
	}

	public ClientePerfilPK getId() {
		return id;
	}

	public void setId(ClientePerfilPK id) {
		this.id = id;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
