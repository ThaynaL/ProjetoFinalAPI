package org.serratec.backend.service;
import org.serratec.backend.dto.ItemDTO;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.entity.ItemPedido;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.entity.StatusPedido;
import org.serratec.backend.repository.ItemPedidoRepository;
import org.serratec.backend.repository.PedidoRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

//    @Autowired
//    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    //get
    public List<PedidoResponseDTO> listarPedidosPorCliente(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(PedidoResponseDTO::new)
                .collect(java.util.stream.Collectors.toList());
    }

    public Page<PedidoResponseDTO> listarPorPagina(Pageable pageable){
        return pedidoRepository.findAll(pageable).map(PedidoResponseDTO::new);

    }

    public PedidoResponseDTO buscarPorId(Long id){
        return pedidoRepository.findById(id).map(PedidoResponseDTO::new).orElseThrow(()
                -> new RuntimeException("Pedido informado nao foi encontrado!"));
    }

    //delete
    public void deletarPedido(Long id){
        if(!pedidoRepository.findById(id).isPresent()){
            throw new RuntimeException("Pedido informado nao foi encontrado!");
        }
        pedidoRepository.deleteById(id);
    }

    //post
    public PedidoResponseDTO criarPedido(PedidoRequestDTO pedidoDTO){

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.EM_ANDAMENTO);

        pedido = pedidoRepository.save(pedido);
        List<ItemPedido> itenSalvos = new ArrayList<>();

        for (ItemDTO itemDTO : pedidoDTO.getItens()) {
            Optional<Produto> produto = produtoRepository.findById(itemDTO.getProduto().getId());

        }
        pedido = pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedido);
    }

    //put
    public PedidoResponseDTO atualizarPedido(Long id, PedidoRequestDTO dto){
        Pedido pedidoExistente = pedidoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Pedido informado nao foi encontrado!"));
        pedidoExistente.setId(id);
        pedidoExistente.setDataPedido(LocalDateTime.now());
        pedidoExistente.setStatusPedido(StatusPedido.valueOf("Em_Andamento"));

        for (ItemDTO itemDTO : dto.getItens()) {
        }
        pedidoRepository.save(pedidoExistente);
        return new PedidoResponseDTO(pedidoExistente);
    }

}
