package com.projects.controle_financeiro.adapter.in.dto.movimentacao;

public record MovimentacaoRequest(
    String descricao,
    Double valor,
    String tipoMovimentacao,
    Integer contaId
) {}
