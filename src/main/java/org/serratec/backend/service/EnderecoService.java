package org.serratec.backend.service;

import org.serratec.backend.dto.EnderecoResponseDTO;
import org.serratec.backend.entity.Endereco;
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

    public EnderecoResponseDTO buscarCep(String cep) {
        var endereco = Optional.ofNullable(repository.findByCep(cep));
        if(endereco.isPresent()) {
           return new EnderecoResponseDTO(endereco.get());
        }else{
           RestTemplate restTemplate = new RestTemplate();
           String url = "https://viacep.com.br/ws/"+cep+"/json/";
           Optional<Endereco> enderecoViaCep = Optional.ofNullable(restTemplate.getForObject(url, Endereco.class));

           if (enderecoViaCep.isPresent()) {
               String cepSemFormatacao = enderecoViaCep.get().getCep().replace("-", "");
               enderecoViaCep.get().setCep(cepSemFormatacao);
               return inserir(enderecoViaCep.get());
           }
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    public EnderecoResponseDTO inserir(Endereco endereco) {
        return new EnderecoResponseDTO(repository.save(endereco));
    }

    public Endereco criarEnderecoPorCep(String cep) {
        Optional<Endereco> endereco = Optional.ofNullable(repository.findByCep(cep));
        return endereco.orElseGet( () -> {
            EnderecoResponseDTO endDTO = buscarCep(cep);
            Endereco enderecoEntity = new Endereco();
            enderecoEntity.setCep(endDTO.cep());
            enderecoEntity.setLogradouro(endDTO.logradouro());
            enderecoEntity.setBairro(endDTO.bairro());
            enderecoEntity.setLocalidade(endDTO.localidade());
            enderecoEntity.setUf(endDTO.uf());
            return repository.save(enderecoEntity);
        });
    }


}
