package com.projects.controle_financeiro.adapter.in.dto.registro;

import java.time.LocalDate;

public record RegistroRequest(
    String motivo,
    String tipoRegistro,
    Double valor,
    String periodo,
    LocalDate ultimoRegistro,
    Boolean ativo,
    Boolean pago,
    Integer contaId
) {}
