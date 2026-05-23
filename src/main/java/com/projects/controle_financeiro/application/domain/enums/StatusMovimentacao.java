package com.projects.controle_financeiro.application.domain.enums;

public enum StatusMovimentacao {
    ENVIADO("Movimentação Enviada"),
    REGISTRADO("Movimentação Registrada");

    private final String status;
    StatusMovimentacao(String status){this.status = status;}
    public String getStatus() {return status;}
}
