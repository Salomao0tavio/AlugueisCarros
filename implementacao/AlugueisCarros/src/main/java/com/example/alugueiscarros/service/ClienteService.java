package com.example.alugueiscarros.service;

import com.example.alugueiscarros.entity.Cliente;
import com.example.alugueiscarros.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente adicionarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> obterClientePorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente atualizarCliente(Integer id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setRg(clienteAtualizado.getRg());
            cliente.setCpf(clienteAtualizado.getCpf());
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEndereco(clienteAtualizado.getEndereco());
            cliente.setProfissao(clienteAtualizado.getProfissao());
            cliente.setLogin(clienteAtualizado.getLogin());
            cliente.setSenha(clienteAtualizado.getSenha());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
    }

    public void deletarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}
