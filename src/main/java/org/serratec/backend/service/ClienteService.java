package org.serratec.backend.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    //Post
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

        repository.save(clienteSalvar);

        // associa os perfis
        for (Long idPerfil : clienteDTO.getIdsPerfis()) {
            ClientePerfil clientePerfil = new ClientePerfil();
            clientePerfil.setCliente(clienteSalvar);
            clientePerfil.setPerfil(perfilService.buscar(idPerfil));
            clientePerfil.setDataCriacao(LocalDate.now());
            clientePerfilRepository.save(clientePerfil);
        }

        // 
        /*
        mailConfig.enviar(
            clienteSalvar.getEmail(),
            "Confirmação de cadastro",
            "Olá, " + clienteSalvar.getNome() + "! Seu cadastro foi realizado com sucesso."
        );
        */

        return new ClienteResponseDTO(clienteSalvar);
    }



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
        clienteExistente.setDataNascimento(dto.getDataNascimento());

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
