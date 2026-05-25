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
public class RendaRecorrente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoRenda;
    private Double valor;
    private String periodo;
    private LocalDateTime ultimaEntrada;
    private Boolean ativo;

    @ManyToOne @JoinColumn(name = "conta_id")
    private ContaBancaria conta;
}
