package com.projects.controle_financeiro.adapter.in.dto.mapper;

import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoRequest;
import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoResponse;
import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;

public class MovimentacaoMapper {
    public static Movimentacao toEntity(MovimentacaoRequest request){
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setData(request.data());
        movimentacao.setDescricao(request.descricao());
        movimentacao.setRecorrente(request.recorrente());
        movimentacao.setTipoMovimentacao(request.tipoMovimentacao().toUpperCase());
        movimentacao.setValor(request.valor());
        return movimentacao;
    }

    public static MovimentacaoResponse toResponse(Movimentacao movimentacao){
        return new MovimentacaoResponse(movimentacao.getId(),movimentacao.getDescricao(),movimentacao.getData(),
                movimentacao.getStatus(),movimentacao.getRecorrente(),movimentacao.getPeriodo(),movimentacao.getTipoMovimentacao(),movimentacao.getConta());
    }
}
