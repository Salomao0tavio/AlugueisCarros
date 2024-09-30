package com.example.alugueiscarros.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "cliente")
public class Cliente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String profissao;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;


    @Column(name = "entidade_empregadora", nullable = true)
    private String entidadeEmpregadora;


    @Column(name = "rendimento", nullable = true)
    private Double rendimento;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedido = new ArrayList<>();
}
