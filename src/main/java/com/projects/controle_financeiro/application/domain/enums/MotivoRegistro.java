package com.projects.controle_financeiro.application.domain.enums;

public enum MotivoRegistro {
    SALARIO("SALARIO"),
    ALIMENTACAO("ALIMENTACAO"),
    TRANSPORTE("TRANSPORTE"),
    SAUDE("SAUDE"),
    FACULDADE("FACULDADE"),
    CURSOS("CURSOS"),
    INTERNET("INTERNET"),
    LAZER("LAZER"),
    INVESTIMENTO("INVESTIMENTO"),
    MESADA("MESADA"),
    PRESENTES("PRESENTES"),
    OUTROS("OUTROS");

    private final String motivoRegistro;
    MotivoRegistro(String motivoRegistro){this.motivoRegistro = motivoRegistro;}
    public String getMotivoRegistro() {return motivoRegistro;}
}
