package com.projects.controle_financeiro.application.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AlertaAtraso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataVencimento;
    private Boolean finalizado;
    @ManyToOne @JoinColumn(name = "conta_id")
    private ContaBancaria conta;
    @ManyToOne @JoinColumn(name = "registro_id")
    private RegistroFinanceiro registro;
}
