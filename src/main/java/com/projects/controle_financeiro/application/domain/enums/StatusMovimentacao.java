package com.projects.controle_financeiro.application.domain.enums;

public enum StatusMovimentacao {
    PAGAR("PAGA"),
    AGENDAR("AGENDADA");

    private final String status;
    StatusMovimentacao(String status){this.status = status;}
    public String getStatus() {return status;}
}
