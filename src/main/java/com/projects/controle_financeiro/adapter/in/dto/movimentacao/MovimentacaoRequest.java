package com.projects.controle_financeiro.adapter.in.dto.movimentacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MovimentacaoRequest (
        @NotBlank String descricao,
        @NotNull Double valor,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @NotNull LocalDateTime data,
        @NotBlank String status,
        String periodo,
        @NotNull Boolean recorrente,
        @NotBlank String tipoMovimentacao,
        @NotNull Long contaId
){}
