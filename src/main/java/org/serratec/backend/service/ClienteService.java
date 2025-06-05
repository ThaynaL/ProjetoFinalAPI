package org.serratec.backend.service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.serratec.backend.config.MailConfig;
import org.serratec.backend.dto.ClienteRequestDTO;
import org.serratec.backend.dto.ClienteResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.ClientePerfil;
import org.serratec.backend.entity.Endereco;
import org.serratec.backend.exception.ClienteException;
import org.serratec.backend.repository.ClientePerfilRepository;
import org.serratec.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

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
    
    public List<ClienteResponseDTO> listar() {
       List<Cliente> clientes = repository.findAll();
       return clientes.stream().map(ClienteResponseDTO::new).collect(Collectors.toList());
    }


    public void deletar(UUID clienteId) {
        if (!repository.existsById(clienteId)) {
            throw new ClienteException("Id não encontrado");
        }
        repository.deleteById(clienteId);
    }

    @Transactional
    public ClienteResponseDTO inserir(ClienteRequestDTO clienteDTO) {
        Optional<Cliente> optionalCliente = repository.findByEmail(clienteDTO.getEmail());
        if (optionalCliente.isPresent()) {
            if (!optionalCliente.get().getStatus()) {
                throw new ClienteException("Cliente inativo!");
            }
            throw new ClienteException("Email já cadastrado!");
        }

        Endereco endereco = enderecoService.criarEnderecoPorCep(clienteDTO.getCep());

        Cliente clienteSalvar = new Cliente();
        clienteSalvar.setNome(clienteDTO.getNome());
        clienteSalvar.setEmail(clienteDTO.getEmail());
        clienteSalvar.setCpf(clienteDTO.getCpf());
        clienteSalvar.setTelefone(clienteDTO.getTelefone());
        clienteSalvar.setDataNascimento(clienteDTO.getDataNascimento());
        clienteSalvar.setSenha(passwordEncoder.encode(clienteDTO.getSenha()));
        clienteSalvar.setEndereco(endereco);

        Set<ClientePerfil> perfis = new HashSet<>();
        for (ClientePerfil perfil : clienteDTO.getPerfils()) {
            perfil.setPerfil(perfilService.buscar(perfil.getPerfil().getId()));
            perfil.setCliente(clienteSalvar);
            perfil.setDataCriacao(LocalDate.now());
            perfis.add(perfil);
        }

        repository.save(clienteSalvar);
        clientePerfilRepository.saveAll(perfis);

        mailConfig.enviar(clienteSalvar.getEmail(), "Confirmação de cadastro", clienteSalvar.toString());
        return new ClienteResponseDTO(clienteSalvar);
    }


    @Transactional
    public ClienteResponseDTO alterar(UUID id, ClienteRequestDTO dto) {
        Optional<Cliente> cliente = Optional.ofNullable(repository.findById(id).orElseThrow(()-> new ClienteException("ID não encontrado.")));

        repository.findByEmail(dto.getEmail()).ifPresent(c -> {
            throw new ClienteException("Email já cadastrado");
        });

        if (!cliente.get().getStatus()) {
            throw new ClienteException("Cliente inativo!");
        }

        cliente.get().setNome(dto.getNome());
        cliente.get().setEmail(dto.getEmail());
        cliente.get().setCpf(dto.getCpf());
        cliente.get().setTelefone(dto.getTelefone());
        cliente.get().setDataNascimento(dto.getDataNascimento());

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            cliente.get().setSenha(passwordEncoder.encode(dto.getSenha()));
        } else {
            throw new ClienteException("Senha invalida!");
        }

        if (dto.getCep() != null && !dto.getCep().isEmpty()) {
            Endereco novoEndereco = enderecoService.criarEnderecoPorCep(dto.getCep());
            cliente.get().setEndereco(novoEndereco);
        } else {
            throw new ClienteException("CEP invalido!");
        }

        Set<ClientePerfil> perfis = new HashSet<>();
        if (dto.getPerfils() != null && !dto.getPerfils().isEmpty()) {
            for (ClientePerfil novoPerfil : dto.getPerfils()) {
                novoPerfil.setPerfil(perfilService.buscar(novoPerfil.getPerfil().getId()));
                novoPerfil.setCliente(cliente.get());
                novoPerfil.setDataCriacao(LocalDate.now());
                perfis.add(novoPerfil);
            }
        }

        repository.save(cliente.get());
        clientePerfilRepository.saveAll(perfis);

        mailConfig.atualizar(cliente.get().getEmail(), "Alteração de cadastro", cliente.get().toString());
        return new ClienteResponseDTO(cliente.get());
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