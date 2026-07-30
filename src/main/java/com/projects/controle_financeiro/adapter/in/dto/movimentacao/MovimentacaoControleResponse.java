package com.projects.controle_financeiro.adapter.in.dto.movimentacao;

import java.time.LocalDate;

public record MovimentacaoControleResponse(
    String descricao,
    String tipo,
    LocalDate dataMovimentacao,
    Double valor
) {}
