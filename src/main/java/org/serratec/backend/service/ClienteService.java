package org.serratec.backend.service;
import org.serratec.backend.config.MailConfig;
import org.serratec.backend.dto.ClienteRequestDTO;
import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.dto.EnderecoResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.ClientePerfil;
import org.serratec.backend.entity.Endereco;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.exception.ClienteException;
import org.serratec.backend.repository.ClientePerfilRepository;
import org.serratec.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
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

    @Autowired
    private ClientePerfilRepository clientePerfilRepository;
    
    @Autowired
    private PerfilService perfilService;
    
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
    @Transactional
    public ClienteResponseDTO inserir(ClienteRequestDTO cliente) {
        Optional<Cliente> optionalCliente = repository.findByEmail(cliente.getEmail());
        if (optionalCliente.isPresent()) {
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

        for (ClientePerfil up : cliente.getClientePerfis()){
            up.setPerfil(perfilService.buscar(up.getPerfil().getId()));
            up.setCliente(clienteSalvar);
            up.setDataCriacao(LocalDate.now());
        }
        repository.save(clienteSalvar);
        clientePerfilRepository.saveAll(cliente.getClientePerfis());

        //mailConfig.enviar(clienteSalvar.getEmail(), "Confirmação de cadastro", clienteSalvar.toString());

        return new ClienteResponseDTO(clienteSalvar);
    }

    //Put
    public ClienteResponseDTO alterar(UUID id, ClienteRequestDTO dto) {
        Optional<Cliente> optionalCliente = repository.findById(id);
        if (optionalCliente.isEmpty()) {
            throw new ClienteException("Cliente não encontrado!");
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
}
