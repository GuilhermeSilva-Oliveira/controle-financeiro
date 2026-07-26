package com.projects.controle_financeiro.application.domain.enums;

public enum TipoConta {
    CORRENTE("Conta Corrente"),
    POUPANCA("Conta Poupança"),
    SALARIO("Conta Salário"),
    INVESTIMENTO("Conta Investimento"),
    CARTEIRA("Carteira Digital");

    private final String tipoConta;
    TipoConta(String tipoConta){this.tipoConta = tipoConta;}
    public String getTipoConta() {return tipoConta;}
}
