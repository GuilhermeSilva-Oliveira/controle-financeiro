package com.projects.controle_financeiro.application.domain.strategy;

public interface MovimentacaoStrategy {
    Double movimentar(Double saldoAtual, Double valorMovimentacao);
    Boolean supports(String tipoMovimentacao);
}
