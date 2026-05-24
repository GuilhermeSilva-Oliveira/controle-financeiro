package com.projects.controle_financeiro.adapter.in.dto.movimentacao;

import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MovimentacaoResponse (
        Long id,
        String descricao,
        LocalDateTime data,
        String status,
        Boolean recorrente,
        String periodo,
        String tipoMovimentacao,
        ContaBancaria conta
){}