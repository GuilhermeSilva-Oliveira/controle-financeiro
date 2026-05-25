package com.projects.controle_financeiro.adapter.in.dto.despesa;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DespesaRequest(
        @NotBlank String descricao,
        @NotNull Double valor,
        @NotBlank String periodo,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @NotNull LocalDateTime dataVencimento,
        @NotNull Boolean ativo,
        @NotNull Boolean parcelado,
        @NotNull Long contaId
) {}
