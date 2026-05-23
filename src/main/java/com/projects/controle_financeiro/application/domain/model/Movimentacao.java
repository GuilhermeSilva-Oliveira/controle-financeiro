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
    private LocalDateTime data;
    private String status;
    private Boolean recorrente;

    @ManyToOne @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne @JoinColumn(name = "conta_id")
    private ContaBancaria conta;
}
