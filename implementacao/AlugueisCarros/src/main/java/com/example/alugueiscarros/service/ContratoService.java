package com.example.alugueiscarros.service;

import com.example.alugueiscarros.entity.Contrato;
import com.example.alugueiscarros.entity.Pedido;
import com.example.alugueiscarros.enums.TipoParecer;
import com.example.alugueiscarros.repository.ContratoRepository;
import com.example.alugueiscarros.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Contrato adicionarContrato (Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    public Optional<Contrato> obterContratoPorId(Integer id) {
        return contratoRepository.findById(id);
    }

    public List<Contrato> findAllByPedidoId(Integer customerId) {

        return contratoRepository.findByPedido_Id(customerId);
    }

    public Contrato atualizarContrato(Integer id, Contrato contratoAtualizado) {
        return contratoRepository.findById(id).map(contrato -> {
            contrato.setContratoTipo(contratoAtualizado.getContratoTipo());
            contrato.setPedido(contratoAtualizado.getPedido());
            contrato.setDataInicio(contratoAtualizado.getDataInicio());
            contrato.setDataFim(contratoAtualizado.getDataFim());
            contrato.setTipoParecer(contratoAtualizado.getTipoParecer());


            return contratoRepository.save(contrato);
        }).orElseThrow(() -> new RuntimeException("Contrato não encontrado com id: " + id));
    }

    public Contrato executarContrato(Integer id) {
        return contratoRepository.findById(id).map(contrato -> {
            contrato.setTipoParecer(TipoParecer.POSITIVO);

            return contratoRepository.save(contrato);
        }).orElseThrow(() -> new RuntimeException("Contrato não encontrado com id: " + id));
    }

    public void deletarContrato(Integer id) {
        contratoRepository.deleteById(id);
    }
}
