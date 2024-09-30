package com.example.alugueiscarros.repository;

import com.example.alugueiscarros.entity.Contrato;
import com.example.alugueiscarros.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
    List<Contrato> findByPedido_Id(Integer pedidoId);
}
