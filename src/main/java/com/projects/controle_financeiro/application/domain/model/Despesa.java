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
public class Despesa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;
    private String periodo;

    @Column(name = "data_vencimento")
    private LocalDateTime dataVencimento;
    private Boolean ativo;
    private Boolean parcelado;

    @ManyToOne @JoinColumn(name = "conta_id")
    private ContaBancaria conta;
}
