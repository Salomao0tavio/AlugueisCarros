package com.example.alugueiscarros.entity;

import jakarta.persistence.*;
import com.example.alugueiscarros.enums.TipoParecer;
import lombok.Data;

@Entity
@Data
@Table(name = "banco")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    public Banco() {
    }

    public Banco(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public boolean fazerLoginBanco(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }
}
