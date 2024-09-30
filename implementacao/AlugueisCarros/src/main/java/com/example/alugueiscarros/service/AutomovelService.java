package com.example.alugueiscarros.service;

import com.example.alugueiscarros.entity.Automovel;
import com.example.alugueiscarros.repository.AutomovelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomovelService {

    @Autowired
    private AutomovelRepository automovelRepository;

    public Automovel salvarAutomovel(Automovel automovel) {
        return automovelRepository.save(automovel);
    }

    public Automovel consultarAutomovel(Long id) {
        return automovelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Automóvel não encontrado"));
    }

    public Automovel modificarAutomovel(Long id, Automovel automovel) {
        Automovel automovelExistente = consultarAutomovel(id);
        automovelExistente.setAno(automovel.getAno());
        automovelExistente.setMarca(automovel.getMarca());
        automovelExistente.setPlaca(automovel.getPlaca());
        automovelExistente.setModelo(automovel.getModelo());
        return automovelRepository.save(automovelExistente);
    }

    public void cancelarAutomovel(Long id) {
        automovelRepository.deleteById(id);
    }
}
