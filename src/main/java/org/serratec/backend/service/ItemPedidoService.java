package org.serratec.backend.service;

import org.serratec.backend.dto.ItemDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.entity.ItemPedido;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.repository.ItemPedidoRepository;
import org.serratec.backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    public ItemDTO inserir(ItemDTO itemDTO){

        //                if(produto.isPresent()){
        //                Produto produtoExistente = produto.get();
        //                ItemPedido itemPedido = new ItemPedido();
        //                itemPedido.setPedido(pedido);
        //                itemPedido.setProduto(produtoExistente);
        //                itemPedido.setQuantidade(itemDTO.getQuantidade());
        //                itemPedido.setValorUnitario(itemDTO.getValorUnitario());
        //                itemPedido = itemPedidoRepository.save(itemPedido);
        //            }


        ItemPedido itemPedido = new ItemPedido();
        //itemPedido.setPedido(itemDTO.getPedido());
        //itemPedido.setProduto(itemDTO.getProduto());
        itemPedido.setQuantidade(itemDTO.getQuantidade());
        itemPedido.setValorUnitario(itemDTO.getValorUnitario());
        itemPedidoRepository.save(itemPedido);
        return new ItemDTO(itemPedido);
    }

















//    public Pedido listar(Long id){
//        Optional<ItemPedido> pedido = itemPedidoRepository.findById(id);
//        return pedido.get().getId().getPedido();
//    }

//    public List <ItemDTO> listarItemPedido(Long id){
//        Optional<ItemPedido> pedido = itemPedidoRepository.findById(id);
//        pedido.get();
//    }


//    public ItemDTO inserir(I endereco) {
//        return new EnderecoResponseDTO(repository.save(endereco));
//    }
//
//    public Endereco criarEnderecoPorCep(String cep) {
//        String cepSemFormatacao = cep.replace("-", "");
//        Optional<Endereco> endereco = Optional.ofNullable(repository.findByCep(cepSemFormatacao));
//
//        return endereco.orElseGet(() -> {
//            Endereco enderecoEntity = buscarCep(cepSemFormatacao);
//            return repository.save(enderecoEntity);
//        });
//    }


}





//    public PedidoResponseDTO busucarPorId(Long id){
//        Optional<Pedido> pedido = pedidoRepository.findById(id);
//      return new PedidoResponseDTO(pedido.get());
//    }
//    public List<PedidoResponseDTO> listar(){
//        List<Pedido> pedidos = pedidoRepository.findAll();
//        return pedidos.stream().map(pedido
//                -> new PedidoResponseDTO(pedido)).collect(Collectors.toList());
//
//    }


