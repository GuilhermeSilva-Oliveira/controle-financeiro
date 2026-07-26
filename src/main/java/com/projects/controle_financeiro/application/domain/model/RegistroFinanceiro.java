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
public class RegistroFinanceiro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String motivo;
    private String tipoRegistro;
    private Double valor;
    private LocalDate ultimoRegistro;
    private LocalDate vencimentoRegistro;
    private String periodo;
    private Boolean ativo;
    private Boolean pago;
    @ManyToOne @JoinColumn(name = "conta_id")
    private ContaBancaria conta;
}
