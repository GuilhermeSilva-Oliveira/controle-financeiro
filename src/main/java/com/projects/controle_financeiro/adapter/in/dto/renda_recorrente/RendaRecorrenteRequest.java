package com.projects.controle_financeiro.adapter.in.dto.renda_recorrente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RendaRecorrenteRequest (
        @NotBlank String tipoRenda,
        @NotNull Double valor,
        @NotBlank String periodo,
        @NotNull LocalDateTime ultimaEntrada,
        @NotNull Long contaId
){}
