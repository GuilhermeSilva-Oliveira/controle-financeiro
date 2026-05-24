package com.projects.controle_financeiro.application.domain.enums;

public enum TipoMovimentacao {
    RECEITA("RECEITA"),
    DESPESA("DESPESA");

    private final String tipo;
    TipoMovimentacao(String tipo){this.tipo = tipo;}
    public String getStatus() {return tipo;}
}
