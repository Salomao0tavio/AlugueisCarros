package com.example.alugueiscarros.service;

import com.example.alugueiscarros.entity.Cliente;
import com.example.alugueiscarros.entity.Pedido;
import com.example.alugueiscarros.repository.ClienteRepository;
import com.example.alugueiscarros.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido adicionarPedido (Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> obterPedidoPorId(Integer id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> findAllByClienteId(Integer customerId) {

        return pedidoRepository.findByCliente_Id(customerId);
    }

    public Pedido atualizarPedido(Integer id, Pedido pedidoAtualizado) {
        System.out.println(pedidoAtualizado);
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setCliente(pedidoAtualizado.getCliente());
            pedido.setData(pedidoAtualizado.getData());
            pedido.setStatus(pedidoAtualizado.getStatus());
            pedido.setCreditoAssociado(pedidoAtualizado.getCreditoAssociado());


            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + id));
    }

    public void deletarPedido(Integer id) {
        pedidoRepository.deleteById(id);
    }

}
