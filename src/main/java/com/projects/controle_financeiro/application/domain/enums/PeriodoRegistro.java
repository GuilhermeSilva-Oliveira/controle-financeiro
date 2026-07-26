package com.projects.controle_financeiro.application.domain.enums;

public enum PeriodoRegistro {
    MENSAL("Registro Financeiro Mensal"),
    ANUAL("Registro Financeiro Anual"),
    DIARIO("Registro Financeiro Diário");

    private final String periodoRegistro;
    PeriodoRegistro(String periodoRegistro){this.periodoRegistro = periodoRegistro;}
    public String getPeriodoRegistro() {return periodoRegistro;}
}
