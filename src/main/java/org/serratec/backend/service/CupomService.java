package org.serratec.backend.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.entity.Cupom;
import org.serratec.backend.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupomService {

    @Autowired
    private CupomRepository cupomRepository;

    // Método para salvar um novo cupom
    public Cupom salvarCupom(Cupom cupom) {
        return cupomRepository.save(cupom);
    }

    // Método para buscar todos os cupons
    public List<Cupom> listarCupons() {
        return cupomRepository.findAll();
    }

    // Método para buscar um cupom pelo ID
    public Optional<Cupom> buscarPorId(Long id) {
        return cupomRepository.findById(id);
    }

    // Método para deletar um cupom pelo ID
    public void deletarCupom(Long id) {
        cupomRepository.deleteById(id);
    }

    // Método para buscar um cupom pelo código (se quiser)
    public Optional<Cupom> buscarPorCodigo(String codigo) {
        return cupomRepository.findByCodigo(codigo);
    }
}
