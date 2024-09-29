package com.example.alugueiscarros.repository;

import com.example.alugueiscarros.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente_Id(Integer clienteId);
}
