package org.serratec.backend.service;
import org.serratec.backend.dto.ItemDTO;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.entity.ItemPedido;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.entity.StatusPedido;
import org.serratec.backend.repository.ItemPedidoRepository;
import org.serratec.backend.repository.PedidoRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    /**
     * Criar pedido
     */

    public Pedido criarPedido(PedidoRequestDTO dto){
      Pedido pedido = new Pedido();
      pedido.setDataPedido(LocalDateTime.now());
      pedido.setStatusPedido(StatusPedido.valueOf("Em_Andamento"));
      for (ItemDTO itemDTO : dto.getItens()) {
          Produto produto = produtoRepository.findById
                  (itemDTO.getId()).orElseThrow(()
                  -> new RuntimeException("O produto informado nao foi encontrado!"));

          ItemPedido itemPedido = new ItemPedido();
          itemPedido.setPedido(pedido);
          itemPedido.setProduto(produto);
          itemPedido.setQuantidade(Math.toIntExact(itemDTO.getQuantidade()));
          itemPedido.setValorUnitario(produto.getValorProduto());
          itemPedido.setValorVenda(itemPedido.getValorVenda().multiply(itemPedido.getQuantidade()));
          pedido = pedidoRepository.save(pedido);
      }
      return pedido;
    }

    /**
     * Listar pedidos
     */

    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }

    /**
     * Buscar pedido por id
     */

    public Optional<Pedido> buscarPorId(Long id){
        return pedidoRepository.findById(id);
    }

    /**
     * Atualizar pedido
     */

    public Pedido atualizarPedido(UUID id, PedidoRequestDTO dto){
        Pedido pedidoExistente = pedidoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Pedido informado nao foi encontrado!"));
        pedidoExistente.setDataPedido(LocalDateTime.now());
        pedidoExistente.setStatusPedido(StatusPedido.valueOf("Em_Andamento"));
        for (ItemDTO itemDTO : dto.getItens()) {}
        return pedidoExistente;
    }

    public Void deletarPedido(UUID id){
        if(!pedidoRepository.findById(id)){
            throw new RuntimeException("Pedido informado nao foi encontrado!");
        }
        pedidoRepository.deleteById(id);
        return null;
    }

}
