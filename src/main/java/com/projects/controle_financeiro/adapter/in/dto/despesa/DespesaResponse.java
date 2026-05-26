package com.projects.controle_financeiro.adapter.in.dto.despesa;

import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DespesaResponse(
        Long id,
        String descricao,
        Double valor,
        String periodo,
        LocalDateTime dataVencimento,
        Boolean ativo,
        Boolean paga,
        Boolean parcelado,
        ContaBancaria conta
) {}
