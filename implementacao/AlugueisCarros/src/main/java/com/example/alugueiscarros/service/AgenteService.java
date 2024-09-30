package com.example.alugueiscarros.service;

import com.example.alugueiscarros.entity.Agente;
import com.example.alugueiscarros.entity.Banco;
import com.example.alugueiscarros.enums.PedidoStatus;
import com.example.alugueiscarros.repository.AgenteRepository;
import com.example.alugueiscarros.entity.Pedido;
import com.example.alugueiscarros.repository.BancoRepository;
import com.example.alugueiscarros.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AgenteService extends UsuarioService<Agente> {

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

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

    public Banco consultarBanco(Long bancoId) {
        return bancoRepository.findById(bancoId)
                .orElseThrow(() -> new EntityNotFoundException("Banco não encontrado"));
    }

    public boolean avaliarPedido(Pedido pedido) {
        boolean aprovado = (pedido.getCliente() != null && pedido.getAutomovel() != null);
        pedido.setStatus(aprovado ? PedidoStatus.APROVADO : PedidoStatus.REJEITADO);
        pedidoRepository.save(pedido);
        return aprovado;
    }


    public void modificarPedido(int pedidoId, PedidoStatus status) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        pedido.setStatus(status);
        pedidoRepository.save(pedido);
    }
}