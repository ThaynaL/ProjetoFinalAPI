package org.serratec.backend.entity.pk;

import java.util.Objects;

import org.serratec.backend.entity.Perfil;
import org.serratec.backend.entity.Cliente;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ClientePerfilPK {

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		return Objects.hash(perfil, cliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientePerfilPK other = (ClientePerfilPK) obj;
		return Objects.equals(perfil, other.perfil) && Objects.equals(cliente, other.cliente);
	}
	
	
	
}
