package com.projects.controle_financeiro.application.domain.enums;

public enum TipoCategoria {
    RECEITA("Categoria de Receita"),
    DESPESA("Categoria de Despesa");

    private final String despresa;
    TipoCategoria(String despresa){this.despresa = despresa;}
    public String getDespresa() {return despresa;}
}
