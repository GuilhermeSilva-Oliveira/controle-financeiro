package com.projects.controle_financeiro.adapter.in.dto.conta_bancaria;

import com.projects.controle_financeiro.application.domain.model.Usuario;

public record ContaBancariaResponse (
        Long id,
        String nomeBanco,
        Double saldo,
        String tipoConta,
        Usuario usuario
){}