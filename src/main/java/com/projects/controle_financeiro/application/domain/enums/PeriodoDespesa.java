package com.projects.controle_financeiro.application.domain.enums;

public enum PeriodoDespesa {
    ANUAL("Período Anual"),
    MENSAL("Período Mensal"),
    DIARIO("Período Diário");

    private final String periodo;
    PeriodoDespesa(String periodo){this.periodo = periodo;}
    public String getPeriodo() {return periodo;}
}
