package com.example.alugueiscarros.service;

import com.example.alugueiscarros.entity.Agente;
import com.example.alugueiscarros.enums.PedidoStatus;
import com.example.alugueiscarros.repository.AgenteRepository;
import com.example.alugueiscarros.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AgenteService extends UsuarioService<Agente> {

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
        agenteExistente.setNome(agente.getNome());
        return agenteRepository.save(agenteExistente);
    }

    public void consultarBanco() {
        //TODO: implementacao
    }

    public boolean avaliarPedido(Pedido pedido) {
        //TODO: implementacao
        return pedido.getStatus().equals("PENDENTE");
    }

    public void modificarPedido(Pedido pedido, PedidoStatus status) {
        //TODO: implementacao
        pedido.setStatus(status);
    }
}
