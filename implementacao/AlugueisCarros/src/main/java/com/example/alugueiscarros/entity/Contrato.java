package com.example.alugueiscarros.entity;

import com.example.alugueiscarros.enums.ContratoTipo;
import com.example.alugueiscarros.enums.TipoParecer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "agente_id")
    private Agente agente;*/

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContratoTipo contratoTipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoParecer tipoParecer;

}
