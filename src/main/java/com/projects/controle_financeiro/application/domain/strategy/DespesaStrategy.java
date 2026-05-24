package com.projects.controle_financeiro.application.domain.strategy;

import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeBadRequestException;
import org.springframework.stereotype.Component;

@Component
public class DespesaStrategy implements MovimentacaoStrategy{
    @Override
    public Double movimentar(Double saldoAtual, Double valorMovimentacao) {
        if (saldoAtual < valorMovimentacao){throw new EntidadeBadRequestException("Saldo Insuficiente");}
        return saldoAtual - valorMovimentacao;
    }

    @Override
    public Boolean supports(String tipoMovimentacao) {
        return TipoMovimentacao.DESPESA.getStatus().equals(tipoMovimentacao.toUpperCase());
    }
}
