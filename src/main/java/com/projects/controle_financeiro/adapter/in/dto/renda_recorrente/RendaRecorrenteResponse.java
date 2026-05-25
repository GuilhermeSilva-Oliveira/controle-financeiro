package com.projects.controle_financeiro.adapter.in.dto.renda_recorrente;

import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RendaRecorrenteResponse (
        Long id,
        String tipoRenda,
        Double valor,
        String periodo,
        LocalDateTime ultimaEntrada,
        Boolean ativo,
        ContaBancaria conta
){}
