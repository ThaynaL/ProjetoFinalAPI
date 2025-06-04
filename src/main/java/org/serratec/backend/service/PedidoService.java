package org.serratec.backend.service;
import org.serratec.backend.dto.ItemRequestDTO;
import org.serratec.backend.dto.ItemResponseDTO;
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

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private ProdutoRepository produtoRepository;


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


    public void deletarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido informado não foi encontrado!");
        }
        pedidoRepository.deleteById(id);
    }


    public PedidoResponseDTO criarPedido(PedidoRequestDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPedido(pedidoDTO.getStatusPedido()); // usa o status vindo do DTO
        pedido.setDataEntregaPedido(pedidoDTO.getDataEntregaPedido());


        pedido = pedidoRepository.save(pedido);


        List<ItemPedido> itensPedido = new ArrayList<>();

        for (ItemRequestDTO itemDTO : pedidoDTO.getItens()) {
        	Produto produto = produtoRepository.findById(itemDTO.getIdProduto())
        		    .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + itemDTO.getIdProduto()));


            ItemPedido item = new ItemPedido();
            item.setPedido(pedido); // associa o pedido ao item
            item.setProduto(produto); // associa o produto ao item
            item.setQuantidade(itemDTO.getQuantidade());
            item.setValorUnitario(itemDTO.getValorUnitario());
            item.setDescontoPercentual(itemDTO.getDescontoPercentual());

            itensPedido.add(item);
        }


        itemPedidoService.salvarTodos(itensPedido);

        return new PedidoResponseDTO(pedido);
    }


    public PedidoResponseDTO atualizarPedido(Long id, PedidoRequestDTO dto){
        Pedido pedidoExistente = pedidoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Pedido informado nao foi encontrado!"));
        pedidoExistente.setId(id);
        pedidoExistente.setDataPedido(LocalDateTime.now());
        pedidoExistente.setStatusPedido(StatusPedido.valueOf("Em_Andamento"));

        for (ItemRequestDTO itemDTO : dto.getItens()) {
        }
        pedidoRepository.save(pedidoExistente);
        return new PedidoResponseDTO(pedidoExistente);
    }

}
