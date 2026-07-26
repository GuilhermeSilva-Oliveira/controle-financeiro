package com.projects.controle_financeiro.adapter.in.dto.conta;

public record ContaRequest(
    String nomeBanco,
    Double saldo,
    String tipoConta,
    Integer usuarioId
) {}
