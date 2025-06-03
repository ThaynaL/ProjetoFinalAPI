package org.serratec.backend.security;

import java.util.Collection;
import java.util.Collections;

import org.serratec.backend.entity.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ClienteDetails implements UserDetails {

    private final Cliente cliente;

    public ClienteDetails(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String getUsername() {
        return cliente.getEmail(); // usamos o email como login
    }

    @Override
    public String getPassword() {
        return cliente.getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // sem roles por enquanto
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

    public Cliente getCliente() {
        return cliente;
    }
}