package com.example.alugueiscarros.repository;

import com.example.alugueiscarros.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByLogin(String login);
    Cliente findByCpf(String cpf);
}
