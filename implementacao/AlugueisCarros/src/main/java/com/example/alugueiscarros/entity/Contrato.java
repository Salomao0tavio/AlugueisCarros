package com.example.alugueiscarros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contrato")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    /*@OneToOne
    @JoinColumn(name = "agente_id")
    private Agente agente;

    @Column(nullable = false)
    private ContratoTipo contratoTipo;

    @Column(nullable = false)
    private TipoParecer tipoParecer;*/

}