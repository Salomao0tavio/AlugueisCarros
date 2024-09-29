package com.example.alugueiscarros.service;

import com.example.alugueiscarros.entity.Agente;
import com.example.alugueiscarros.repository.AgenteRepository;
import com.example.alugueiscarros.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AgenteService {

    @Autowired
    private AgenteRepository agenteRepository;

    public Agente salvarAgente(Agente agente) {
        return agenteRepository.save(agente);
    }

    public Agente consultarAgente(Long id) {
        return agenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agente não encontrado"));
    }

    public Agente modificarAgente(Long id, Agente agente) {
        Agente agenteExistente = consultarAgente(id);
        // Atualize os campos do agente
        agenteExistente.setNome(agente.getNome());
        agenteExistente.setLogin(agente.getLogin());
        agenteExistente.setSenha(agente.getSenha());
        return agenteRepository.save(agenteExistente);
    }

    public boolean fazerLoginAgente(String login, String senha) {
        Agente agente = agenteRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Login não encontrado"));
        return agente.getSenha().equals(senha);
    }

    public void consultarBanco() {
        // Implementar lógica para consulta ao banco de dados
        // Exemplo fictício:
        System.out.println("Consultando banco de dados...");
    }

    public boolean avaliarPedido(Pedido pedido) {
        // Implementar lógica de avaliação de pedidos
        // Exemplo fictício:
        return pedido.getStatus().equals("PENDENTE");
    }

    public void modificarPedido(Pedido pedido) {
        // Implementar lógica de modificação de pedidos
        pedido.setStatus("MODIFICADO");
        // Salvar a modificação no banco de dados
        // Exemplo fictício:
        System.out.println("Pedido modificado com sucesso.");
    }
}
