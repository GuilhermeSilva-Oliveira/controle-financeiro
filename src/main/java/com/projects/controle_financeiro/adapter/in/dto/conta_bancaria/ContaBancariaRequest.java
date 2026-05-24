package com.projects.controle_financeiro.adapter.in.dto.conta_bancaria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContaBancariaRequest(
        @NotBlank String nomeBanco,
        @NotNull Double saldo,
        @NotBlank String tipoConta,
        @NotNull Long usuarioId
) {}
