package com.projects.controle_financeiro.adapter.in.dto;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        LocalDateTime dataCadastro
) {}
