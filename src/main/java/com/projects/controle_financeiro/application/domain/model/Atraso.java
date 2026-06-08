package com.projects.controle_financeiro.application.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Atraso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "despesa_id")
    private Despesa despesa;
    private String motivo;
    private Boolean finalizado;
    private Double valor;

    @JoinColumn(name = "tx_diaria")
    private Double txDiaria;
}
