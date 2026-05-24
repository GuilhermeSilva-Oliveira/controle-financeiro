package com.projects.controle_financeiro.application.domain.strategy;

import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import org.springframework.stereotype.Component;

@Component
public class ReceitaStrategy implements MovimentacaoStrategy{
    @Override
    public Double movimentar(Double saldoAtual, Double valorMovimentacao) {
        return saldoAtual + valorMovimentacao;
    }

    @Override
    public Boolean supports(String tipoMovimentacao) {
        return TipoMovimentacao.RECEITA.getStatus().equals(tipoMovimentacao.toUpperCase());
    }
}
