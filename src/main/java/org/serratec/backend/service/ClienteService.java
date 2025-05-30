package org.serratec.backend.service;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public Cliente buscar(Long id){
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.get();
    }
}
