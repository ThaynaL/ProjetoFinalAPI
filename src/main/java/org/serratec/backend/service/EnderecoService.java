package org.serratec.backend.service;

import org.serratec.backend.dto.EnderecoResponseDTO;
import org.serratec.backend.entity.Endereco;
import org.serratec.backend.exception.EnderecoException;
import org.serratec.backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public Endereco buscarCep(String cep) {
        String cepSemFormatacao = cep.replace("-", "");
        var endereco = Optional.ofNullable(repository.findByCep(cepSemFormatacao));
        if (endereco.isPresent()) {
            return endereco.get();
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cepSemFormatacao + "/json/";
        Endereco enderecoViaCep = restTemplate.getForObject(url, Endereco.class);
        if (enderecoViaCep != null) {
            enderecoViaCep.setCep(cepSemFormatacao);
            return enderecoViaCep;
        }else {
            throw new EnderecoException("CEP " + cep + " não encontrado.");
        }
    }

    public EnderecoResponseDTO inserir(Endereco endereco) {
        return new EnderecoResponseDTO(repository.save(endereco));
    }

    public Endereco criarEnderecoPorCep(String cep) {
        String cepSemFormatacao = cep.replace("-", "");
        Optional<Endereco> endereco = Optional.ofNullable(repository.findByCep(cepSemFormatacao));

        return endereco.orElseGet(() -> {
            Endereco enderecoEntity = buscarCep(cepSemFormatacao);
            return repository.save(enderecoEntity);
        });
    }
}