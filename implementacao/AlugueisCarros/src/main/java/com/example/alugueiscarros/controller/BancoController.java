package com.example.alugueiscarros.controller;

import com.example.alugueiscarros.entity.Banco;
import com.example.alugueiscarros.enums.TipoParecer;
import com.example.alugueiscarros.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banco")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @PostMapping("/salvar")
    public ResponseEntity<Banco> salvarBanco(@RequestBody Banco banco) {
        Banco salvo = bancoService.salvarBanco(banco);
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/login")
    public ResponseEntity<String> fazerLoginBanco(@RequestParam String login, @RequestParam String senha) {
        boolean loginValido = bancoService.fazerLoginBanco(login, senha);
        if (loginValido) {
            return ResponseEntity.ok("Login bem-sucedido");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    @PutMapping("/liberarCredito/{contratoId}")
    public ResponseEntity<String> liberarCredito(@PathVariable Long contratoId, @RequestParam TipoParecer parecer) {
        bancoService.liberarCredito(contratoId, parecer);
        return ResponseEntity.ok("Parecer financeiro atualizado para: " + parecer);
    }
}
