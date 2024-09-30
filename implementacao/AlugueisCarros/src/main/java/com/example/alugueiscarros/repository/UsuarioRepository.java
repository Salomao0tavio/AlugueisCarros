package com.example.alugueiscarros.repository;

import com.example.alugueiscarros.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UsuarioRepository<T extends Usuario> extends JpaRepository<T, Long> {

    T findByLogin(String login);
}
