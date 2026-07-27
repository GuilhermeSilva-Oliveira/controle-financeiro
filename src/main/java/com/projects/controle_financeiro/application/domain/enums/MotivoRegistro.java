package com.projects.controle_financeiro.application.domain.enums;

public enum MotivoRegistro {
    FACULDADE("FACULDADE"),
    SALARIO("SALARIO"),
    INTERNET("INTERNET"),
    ALIMENTACAO("ALIMENTACAO");

    private final String motivoRegistro;
    MotivoRegistro(String motivoRegistro){this.motivoRegistro = motivoRegistro;}
    public String getMotivoRegistro() {return motivoRegistro;}
}
