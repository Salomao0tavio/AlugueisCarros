package com.example.alugueiscarros.repository;

import com.example.alugueiscarros.entity.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BancoRepository extends JpaRepository<Banco, Long> {
    Optional<Banco> findByLogin(String login);
}
