package org.serratec.backend.service;
import org.serratec.backend.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private PedidoRepository repository;
    public List<PedidosDTO> listarPedidos(){
        return repository.findAll();
    }


}
