package com.projects.controle_financeiro.application.domain.enums;

public enum TipoMovimentacao {
    ENTRADA("Entrada de Valor"),
    SAIDA("Saída de Valor"),
    RECEITA("RECEITA"),
    DESPESA("DESPESA");

    private final String tipoMovimentacao;
    TipoMovimentacao(String tipoMovimentacao){this.tipoMovimentacao = tipoMovimentacao;}
    public String getTipoMovimentacao() {return tipoMovimentacao;}
}
