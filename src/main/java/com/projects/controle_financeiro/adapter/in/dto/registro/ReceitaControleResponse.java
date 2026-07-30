package com.projects.controle_financeiro.adapter.in.dto.registro;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

    public record ReceitaControleResponse(
        String motivo,
        String periodo,
        LocalDate ultimoRegistro,
        Double valor
    ) {}
