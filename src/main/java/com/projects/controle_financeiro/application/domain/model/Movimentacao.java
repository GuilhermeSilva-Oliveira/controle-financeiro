package com.projects.controle_financeiro.application.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movimentacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;

    @Column(name = "data_movimentacao")
    private LocalDateTime data;

    @Column(name = "status_movimentacao")
    private String status;
    private Boolean recorrente;
    private String tipoMovimentacao;
    private String periodo;

    @ManyToOne @JoinColumn(name = "conta_id")
    private ContaBancaria conta;
}
