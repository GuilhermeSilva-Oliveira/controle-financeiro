package com.projects.controle_financeiro.adapter.in.dto.mapper;

import com.projects.controle_financeiro.application.domain.model.Atraso;
import com.projects.controle_financeiro.application.domain.model.Despesa;

public class AtrasoMapper {
    public static Atraso gerarAtraso(Despesa despesa){
        Atraso atraso = new Atraso();
        atraso.setDespesa(despesa);
        atraso.setMotivo(despesa.getDescricao());
        atraso.setFinalizado(false);
        atraso.setValor(despesa.getValor());
        return atraso;
    }
}
