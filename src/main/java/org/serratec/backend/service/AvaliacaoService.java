package org.serratec.backend.service;

import java.util.List;

import org.serratec.backend.dto.AvaliacaoDTO;
import org.serratec.backend.entity.Avaliacao;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.repository.AvaliacaoRepository;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Avaliacao criarAvaliacao(AvaliacaoDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());
        avaliacao.setProduto(produto);
        avaliacao.setCliente(cliente);

        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> listarPorProduto(Long produtoId) {
        return avaliacaoRepository.findByProdutoId(produtoId);
    }

    public Avaliacao atualizarAvaliacao(Long id, AvaliacaoDTO dto) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());

        return avaliacaoRepository.save(avaliacao);
    }

    public void deletarAvaliacao(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}

