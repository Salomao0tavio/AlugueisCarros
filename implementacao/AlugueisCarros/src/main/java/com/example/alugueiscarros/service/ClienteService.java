package com.example.alugueiscarros.service;

import com.example.alugueiscarros.entity.Cliente;
import com.example.alugueiscarros.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public Cliente adicionarCliente(Cliente cliente) {
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
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

            // Adicione tratamento para entidadesEmpregadoras e rendimento
            cliente.setEntidadeEmpregadora(clienteAtualizado.getEntidadeEmpregadora() != null ? clienteAtualizado.getEntidadeEmpregadora() : "Não informado");
            cliente.setRendimento(clienteAtualizado.getRendimento() != null ? clienteAtualizado.getRendimento() : 0.0);

            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
    }



    public void deletarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}
