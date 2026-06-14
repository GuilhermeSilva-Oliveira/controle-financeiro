package com.projects.controle_financeiro.application.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private Double valorInicial;
    private Double montante; // montante
    private Double rentabilidade;
    private Double meta;
    private String instituicao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String tipoRenda; // Renda Fixa ou Variável
    private String motivo;
}
// BIG MATHEUS
// Meta de Investimento (Valor mínimo mensal por exemplo)
// API de busca de boletos associados ao CPF
// Renda - Despesa = Líquido Automático
// IA para Relatório de Despesas
