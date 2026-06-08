package com.projects.controle_financeiro.adapter.in.dto.mapper;

import com.projects.controle_financeiro.adapter.in.dto.renda_recorrente.RendaRecorrenteRequest;
import com.projects.controle_financeiro.adapter.in.dto.renda_recorrente.RendaRecorrenteResponse;
import com.projects.controle_financeiro.application.domain.model.Renda;

public class RendaRecorrenteMapper {
    public static Renda toEntity(RendaRecorrenteRequest request){
        Renda renda = new Renda();
        renda.setAtivo(true);
        renda.setPeriodo(request.periodo());
        renda.setTipoRenda(request.tipoRenda());
        renda.setUltimaEntrada(request.ultimaEntrada());
        renda.setValor(request.valor());
        return renda;
    }

    public static RendaRecorrenteResponse toResponse(Renda renda){
        return new RendaRecorrenteResponse(renda.getId(),renda.getTipoRenda(),renda.getValor(),renda.getPeriodo(),renda.getUltimaEntrada(),
                renda.getAtivo(),renda.getConta());
    }
}
