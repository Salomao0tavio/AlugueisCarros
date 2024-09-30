package com.example.alugueiscarros.controller;

import com.example.alugueiscarros.entity.Agente;
import com.example.alugueiscarros.entity.Cliente;
import com.example.alugueiscarros.service.AgenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agentes")
public class AgenteController {

    @Autowired
    private AgenteService agenteService;

    @PostMapping
    public ResponseEntity<Agente> criarAgente(@RequestBody Agente agente) {
        Agente novoAgente = agenteService.salvarAgente(agente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agente> consultarAgente(@PathVariable Long id) {
        Agente agente = agenteService.consultarAgente(id);
        return ResponseEntity.ok(agente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agente> modificarAgente(@PathVariable Long id, @RequestBody Agente agente) {
        Agente agenteAtualizado = agenteService.modificarAgente(id, agente);
        return ResponseEntity.ok(agenteAtualizado);
    }
    @PostMapping("/registrar")
    public ResponseEntity<Agente> registrar(@RequestBody Agente agente) {
        Agente novoAgente = agenteService.registrar(agente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgente);
    }

    @PostMapping("/login")
    public ResponseEntity<Agente> login(@RequestParam String login, @RequestParam String senha) {
        Agente agente = agenteService.login(login, senha);
        return ResponseEntity.ok(agente);
    }
}
