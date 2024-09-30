package com.example.alugueiscarros.controller;

import com.example.alugueiscarros.entity.Automovel;
import com.example.alugueiscarros.service.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @PostMapping
    public ResponseEntity<Automovel> criarAutomovel(@RequestBody Automovel automovel) {
        Automovel novoAutomovel = automovelService.salvarAutomovel(automovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAutomovel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Automovel> consultarAutomovel(@PathVariable Long id) {
        Automovel automovel = automovelService.consultarAutomovel(id);
        return ResponseEntity.ok(automovel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Automovel> modificarAutomovel(@PathVariable Long id, @RequestBody Automovel automovel) {
        Automovel automovelAtualizado = automovelService.modificarAutomovel(id, automovel);
        return ResponseEntity.ok(automovelAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarAutomovel(@PathVariable Long id) {
        automovelService.cancelarAutomovel(id);
        return ResponseEntity.noContent().build();
    }
}
