package com.projects.controle_financeiro.application.domain.enums;

public enum DescricaoMovimentacao {
    FACULDADE("Mensalidade da Faculdade"),
    INTERNET("Mensalidade da Internet"),
    CABELEREIRO("Corte de Cabelo");

    private final String periodo;
    DescricaoMovimentacao(String periodo){this.periodo = periodo;}
    public String getPeriodo() {return periodo;}
}
