package org.serratec.backend.service;


import java.util.List;

import org.serratec.backend.dto.ItemResponseDTO;
import org.serratec.backend.entity.ItemPedido;
import org.serratec.backend.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public ItemResponseDTO inserir(ItemResponseDTO itemDTO) {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(itemDTO.getQuantidade());
        itemPedido.setValorUnitario(itemDTO.getValorUnitario());
        itemPedidoRepository.save(itemPedido);
        return new ItemResponseDTO(itemPedido);
    }

    public List<ItemPedido> salvarTodos(List<ItemPedido> itens) {
        return itemPedidoRepository.saveAll(itens);
    }
}
