package org.serratec.backend.service;
import org.serratec.backend.config.MailConfig;
import org.serratec.backend.dto.ClienteRequestDTO;
import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.dto.EnderecoResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Endereco;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.exception.ClienteException;
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
       return clientes.stream().map(ClienteResponseDTO::new).collect(Collectors.toList());
    }

    //Delete
    public void deletar(UUID clienteId) {
        if (!repository.existsById(clienteId)) {
            throw new ClienteException("Id não encontrado");
        }
        repository.deleteById(clienteId);
    }

    //Post
    public ClienteResponseDTO inserir(ClienteRequestDTO cliente) {
        Optional<Cliente> optionalCliente = repository.findByEmail(cliente.getEmail());
        if (optionalCliente.isPresent()) {
            if (!optionalCliente.get().getStatus()) {
                throw new ClienteException("Cliente inativo!");
            }
            throw new ClienteException("Email já cadastrado!");
        }

        Endereco endereco = enderecoService.criarEnderecoPorCep(cliente.getCep());

        Cliente clienteSalvar = new Cliente();
        clienteSalvar.setNome(cliente.getNome());
        clienteSalvar.setEmail(cliente.getEmail());
        clienteSalvar.setCpf(cliente.getCpf());
        clienteSalvar.setTelefone(cliente.getTelefone());
        clienteSalvar.setSenha(passwordEncoder.encode(cliente.getSenha()));
        clienteSalvar.setEndereco(endereco);

        repository.save(clienteSalvar);

        //mailConfig.enviar(clienteSalvar.getEmail(), "Confirmação de cadastro", clienteSalvar.toString());

        return new ClienteResponseDTO(clienteSalvar);
    }

    //Put
    public ClienteResponseDTO alterar(UUID id, ClienteRequestDTO dto) {
        Optional<Cliente> optionalCliente = repository.findById(id);
        if (optionalCliente.isEmpty()) {
            throw new ClienteException("Cliente não encontrado!");
        }

        if (!optionalCliente.get().getStatus()){
            throw new ClienteException("Cliente inativo!");
        }
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

    public void ativar(UUID id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ClienteException("O cliente não foi encontrado."));

        if (cliente.getStatus()) {
            throw new ClienteException("Este cliente já está ativo!");
        }

        cliente.setStatus(true);
        repository.save(cliente);
    }

    public void desativar(UUID id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ClienteException("O cliente não foi encontrado."));

        if (!cliente.getStatus()) {
            throw new ClienteException("Este cliente já está desativado!");
        }

        cliente.setStatus(false);
        repository.save(cliente);
    }

}
