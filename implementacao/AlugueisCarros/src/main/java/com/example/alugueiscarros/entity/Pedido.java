package com.example.alugueiscarros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime data;

    //@Column(nullable = false)
    //private PedidoStatus status;

    @Column(nullable = false)
    private Boolean creditoAssociado;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //private Automovel automovel;

    @OneToMany(mappedBy = "pedido")
    private List<Contrato> contrato = new ArrayList<>();

}