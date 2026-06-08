package com.projects.controle_financeiro.adapter.in.dto.mapper;

import com.projects.controle_financeiro.adapter.in.dto.despesa.DespesaRequest;
import com.projects.controle_financeiro.adapter.in.dto.despesa.DespesaResponse;
import com.projects.controle_financeiro.application.domain.model.Despesa;

public class DespesaMapper {
    public static Despesa toEntity(DespesaRequest request){
        Despesa despesa = new Despesa();
        despesa.setDescricao(request.descricao());
        despesa.setAtivo(request.ativo());
        despesa.setPaga(request.paga());
        despesa.setDataVencimento(request.dataVencimento());
        despesa.setParcelado(request.parcelado());
        despesa.setPeriodo(request.periodo());
        despesa.setValor(request.valor());
        return despesa;
    }

    public static DespesaResponse toResponse(Despesa despesa){
        return new DespesaResponse(despesa.getId(),despesa.getDescricao(),despesa.getValor(),despesa.getPeriodo(),despesa.getDataVencimento(),
                despesa.getAtivo(),despesa.getPaga(),despesa.getParcelado(),despesa.getConta());
    }
}
