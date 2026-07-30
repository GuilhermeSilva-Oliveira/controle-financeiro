package com.projects.controle_financeiro.application.domain.enums;

public enum TipoMovimentacao {
    ENTRADA("ENTRADA"),
    SAIDA("SAIDA"),
    RECEITA("RECEITA"),
    DESPESA("DESPESA");

    private final String tipoMovimentacao;
    TipoMovimentacao(String tipoMovimentacao){this.tipoMovimentacao = tipoMovimentacao;}
    public String getTipoMovimentacao() {return tipoMovimentacao;}
}
