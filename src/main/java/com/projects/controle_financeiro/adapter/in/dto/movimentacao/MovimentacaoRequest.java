package com.projects.controle_financeiro.adapter.in.dto.movimentacao;

public record MovimentacaoRequest(
    String motivo,
    Double valor,
    String tipoMovimentacao,
    Boolean isPagandoDespesa,
    Integer contaId
) {}
