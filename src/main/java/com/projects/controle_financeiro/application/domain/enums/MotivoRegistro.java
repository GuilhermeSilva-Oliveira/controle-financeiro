package com.projects.controle_financeiro.application.domain.enums;

public enum MotivoRegistro {
    FACULDADE("Pagar Fatura Faculdade"),
    SALARIO("Recebimento de Salário"),
    INTERNET("Pagar Fatura Internet"),
    ALIMENTACAO("Gasto com Alimentação");

    private final String motivoRegistro;
    MotivoRegistro(String motivoRegistro){this.motivoRegistro = motivoRegistro;}
    public String getMotivoRegistro() {return motivoRegistro;}
}
