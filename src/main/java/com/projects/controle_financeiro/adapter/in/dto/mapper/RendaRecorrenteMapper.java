package com.projects.controle_financeiro.adapter.in.dto.mapper;

import com.projects.controle_financeiro.adapter.in.dto.renda_recorrente.RendaRecorrenteRequest;
import com.projects.controle_financeiro.adapter.in.dto.renda_recorrente.RendaRecorrenteResponse;
import com.projects.controle_financeiro.application.domain.model.RendaRecorrente;

public class RendaRecorrenteMapper {
    public static RendaRecorrente toEntity(RendaRecorrenteRequest request){
        RendaRecorrente renda = new RendaRecorrente();
        renda.setAtivo(true);
        renda.setPeriodo(request.periodo());
        renda.setTipoRenda(request.tipoRenda());
        renda.setUltimaEntrada(request.ultimaEntrada());
        renda.setValor(request.valor());
        return renda;
    }

    public static RendaRecorrenteResponse toResponse(RendaRecorrente renda){
        return new RendaRecorrenteResponse(renda.getId(),renda.getTipoRenda(),renda.getValor(),renda.getPeriodo(),renda.getUltimaEntrada(),
                renda.getAtivo(),renda.getConta());
    }
}
