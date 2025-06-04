package org.serratec.backend.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUuid;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private String cpf;
    private boolean status = Boolean.TRUE;
    private LocalDate dataNascimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Telefone: " + telefone + "\n" +
                "E-mail: " + email + "\n" +
                "CPF: " + cpf + "\n" +
                "Data de nascimento: " + dataNascimento + "\n" +
                "Endereco: " + endereco + "\n" +
                "Pedidos: " + pedidos
                ;
    }

    public UUID getIdUuid() {
        return idUuid;
    }

    public void setIdUuid(UUID idUuid) {
        this.idUuid = idUuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    @Override
    public String getUsername() {
        return email; // usamos o email como login
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (ClientePerfil perfil : clientePerfis) {
            String nomeRole = perfil.getPerfil().getNome(); 
            authorities.add(() -> nomeRole); 
        }
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @OneToMany(mappedBy = "id.cliente", fetch = FetchType.EAGER)
    private Set<ClientePerfil> clientePerfis = new HashSet<>();

	public Set<ClientePerfil> getClientePerfis() {
		return clientePerfis;
	}

	public void setClientePerfis(Set<ClientePerfil> clientePerfis) {
		this.clientePerfis = clientePerfis;
	}
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

