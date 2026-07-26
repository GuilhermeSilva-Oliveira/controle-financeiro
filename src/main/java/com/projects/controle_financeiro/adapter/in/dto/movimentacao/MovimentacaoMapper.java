package com.projects.controle_financeiro.adapter.in.dto.movimentacao;

import com.projects.controle_financeiro.adapter.in.dto.conta.ContaRequest;
import com.projects.controle_financeiro.application.domain.enums.TipoConta;
import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import com.projects.controle_financeiro.application.domain.model.Usuario;

import java.time.LocalDateTime;

public class MovimentacaoMapper {
    public static Movimentacao toEntity(MovimentacaoRequest request, TipoMovimentacao tipo, ContaBancaria conta){
        Movimentacao m = new Movimentacao();
        m.setDescricao(request.descricao());
        m.setValor(request.valor());
        m.setDataMovimentacao(LocalDateTime.now());
        m.setConta(conta);
        m.setTipoMovimentacao(tipo.getTipoMovimentacao());
        return m;
    }
}
