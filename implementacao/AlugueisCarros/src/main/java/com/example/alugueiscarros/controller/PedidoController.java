package com.example.alugueiscarros.controller;

import com.example.alugueiscarros.entity.Cliente;
import com.example.alugueiscarros.entity.Pedido;
import com.example.alugueiscarros.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> adicionarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.adicionarPedido(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPedidoPorId(@PathVariable Integer id) {
        Optional<Pedido> pedido = pedidoService.obterPedidoPorId(id);
        return pedido.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> findAllByClienteId(@PathVariable Integer clienteId) {
        List<Pedido> objs = pedidoService.findAllByClienteId(clienteId);
        return ResponseEntity.ok().body(objs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedidoAtualizado) {
        Pedido cliente = pedidoService.atualizarPedido(id, pedidoAtualizado);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Integer id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
