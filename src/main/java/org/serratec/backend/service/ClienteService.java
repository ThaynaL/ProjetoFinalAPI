package org.serratec.backend.service;
import org.serratec.backend.config.MailConfig;
import org.serratec.backend.dto.ClienteRequestDTO;
import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.dto.EnderecoResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Endereco;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private MailConfig mailConfig;

    //Get
    public List<ClienteResponseDTO> listar() {
       List<Cliente> clientes = repository.findAll();
       return clientes.stream().map(cliente -> new ClienteResponseDTO(cliente)).collect(Collectors.toList());
    }

    //Delete
    public void deletar(UUID clienteId) {
        if (!repository.existsById(clienteId)) {
            //throw new ContaException("Id não encontrado");
        }
        repository.deleteById(clienteId);
    }

    //Post
    public ClienteResponseDTO inserir(ClienteRequestDTO cliente) {
//        repository.findByEmail(cliente.getEmail())
//                .isPresent(c -> throw new ContaException("Email já cadastrado!"));

        Endereco endereco = enderecoService.criarEnderecoPorCep(cliente.getCep());

        Cliente clienteSalvo = new Cliente();
        clienteSalvo.setNome(cliente.getNome());
        clienteSalvo.setEmail(cliente.getEmail());
        clienteSalvo.setCpf(cliente.getCpf());
        clienteSalvo.setTelefone(cliente.getTelefone());
        clienteSalvo.setSenha(passwordEncoder.encode(cliente.getSenha()));
        clienteSalvo.setEndereco(endereco);

        repository.save(clienteSalvo);

        mailConfig.enviar(clienteSalvo.getEmail(), "Confirmação de cadastro", clienteSalvo.toString());

        return new ClienteResponseDTO(clienteSalvo);
    }

    //Put
    public ClienteResponseDTO alterar(UUID id, ClienteRequestDTO dto) {
        Optional<Cliente> optionalCliente = repository.findById(id);
//        if (optionalCliente.isEmpty()) {
//            throw new ContaException("Cliente não encontrado!");
//        }
        Cliente clienteExistente = optionalCliente.get();

        clienteExistente.setNome(dto.getNome());
        clienteExistente.setEmail(dto.getEmail());
        clienteExistente.setCpf(dto.getCpf());
        clienteExistente.setTelefone(dto.getTelefone());

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            clienteExistente.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        if (dto.getCep() != null && !dto.getCep().isEmpty()) {
            Endereco novoEndereco = enderecoService.criarEnderecoPorCep(dto.getCep());
            clienteExistente.setEndereco(novoEndereco);
        }
        mailConfig.atualizar(clienteExistente.getEmail(), "Alteração de cadastro", clienteExistente.toString());
        return new ClienteResponseDTO(repository.save(clienteExistente));
    }
}
