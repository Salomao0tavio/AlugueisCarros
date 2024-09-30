package com.example.alugueiscarros.service;

import com.example.alugueiscarros.entity.Banco;
import com.example.alugueiscarros.entity.Contrato;
import com.example.alugueiscarros.enums.TipoParecer;
import com.example.alugueiscarros.repository.BancoRepository;
import com.example.alugueiscarros.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    public Banco salvarBanco(Banco banco) {
        return bancoRepository.save(banco);
    }

    public Banco consultarBanco(Long id) {
        return bancoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Banco não encontrado"));
    }

    public boolean fazerLoginBanco(String login, String senha) {
        Banco banco = bancoRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Login não encontrado"));
        return banco.getSenha().equals(senha);
    }

    public void liberarCredito(int contratoId, TipoParecer parecer) {
        Contrato contrato = contratoRepository.findById(contratoId)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));
        contrato.setTipoParecer(parecer);
        contratoRepository.save(contrato);
    }
}
