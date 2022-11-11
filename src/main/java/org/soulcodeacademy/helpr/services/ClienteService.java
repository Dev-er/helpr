package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.dto.ClienteDTO;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.soulcodeacademy.helpr.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente>listarCliente(){
        return clienteRepository.findAll();
    }

    public Cliente getCliente(Integer idCliente) {

        Optional<Cliente> cliente = this.clienteRepository.findById(idCliente);

        if(cliente.isEmpty()) {
            throw new RuntimeException("O cliente não foi encontrado");
        }else {
            return cliente.get();
        }
    }

    public Cliente salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getSenha(), dto.getTelefone(), dto.getCnpj());
        Cliente salvo = this.clienteRepository.save(cliente);
        return salvo;
    }

    public Cliente atualizar(Integer idCliente, @Valid ClienteDTO dto) {
        Cliente clienteAtual = this.getCliente(idCliente);

        clienteAtual.setNome(dto.getNome());
        clienteAtual.setEmail(dto.getEmail());
        clienteAtual.setCpf(dto.getCpf());
        clienteAtual.setSenha(dto.getSenha());
        clienteAtual.setTelefone(dto.getTelefone());
        clienteAtual.setCnpj(dto.getCnpj());

        Cliente atualizado = this.clienteRepository.save(clienteAtual);
        return atualizado;

    }

    public void deletar(Integer idCliente) {
        Cliente cliente = this.getCliente(idCliente);
        this.clienteRepository.delete(cliente);
    }
}
