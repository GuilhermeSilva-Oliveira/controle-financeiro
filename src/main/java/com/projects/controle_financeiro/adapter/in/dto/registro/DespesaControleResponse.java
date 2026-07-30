package com.projects.controle_financeiro.adapter.in.dto.registro;

import java.time.LocalDate;

public record DespesaControleResponse(
    String descricao,
    String tipo,
    LocalDate proximoVencimento,
    Double valor
) {}
