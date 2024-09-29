package com.example.alugueiscarros.controller;

import com.example.alugueiscarros.entity.Contrato;
import com.example.alugueiscarros.entity.Contrato;
import com.example.alugueiscarros.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contratos")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;

    /*@PostMapping
    public ResponseEntity<Contrato> gerarContrato(@RequestBody Contrato contrato) {
        Contrato novoContrato = contratoService.gerarContrato(contrato);
        return ResponseEntity.ok(novoContrato);
    }*/

    @PostMapping
    public ResponseEntity<Contrato> adicionarContrato(@RequestBody Contrato contrato) {
        Contrato novoContrato = contratoService.adicionarContrato(contrato);
        return ResponseEntity.ok(novoContrato);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> obterContratoPorId(@PathVariable Integer id) {
        Optional<Contrato> contrato = contratoService.obterContratoPorId(id);
        return contrato.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/pedido/{pedidoId}")
    public ResponseEntity<List<Contrato>> findAllByPedidoId(@PathVariable Integer pedidoId) {
        List<Contrato> objs = contratoService.findAllByPedidoId(pedidoId);
        return ResponseEntity.ok().body(objs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> atualizarContrato(@PathVariable Integer id, @RequestBody Contrato contratoAtualizado) {
        Contrato cliente = contratoService.atualizarContrato(id, contratoAtualizado);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContrato(@PathVariable Integer id) {
        contratoService.deletarContrato(id);
        return ResponseEntity.noContent().build();
    }
}
