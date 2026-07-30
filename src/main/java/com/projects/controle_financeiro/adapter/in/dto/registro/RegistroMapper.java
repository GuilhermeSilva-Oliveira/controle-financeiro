package com.projects.controle_financeiro.adapter.in.dto.registro;

import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoRequest;
import com.projects.controle_financeiro.application.domain.enums.MotivoRegistro;
import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistroMapper {
    public static RegistroFinanceiro toEntity(RegistroRequest request, TipoMovimentacao tipo, MotivoRegistro motivo, ContaBancaria conta, LocalDate dataVencimento){
        RegistroFinanceiro r = new RegistroFinanceiro();
        r.setAtivo(request.ativo());
        r.setConta(conta);
        r.setMotivo(MotivoRegistro.valueOf(request.motivo()).getMotivoRegistro());
        r.setPago(request.pago());
        r.setValor(request.valor());
        r.setPeriodo(request.periodo());
        r.setTipoRegistro(tipo.getTipoMovimentacao());
        r.setUltimoRegistro(request.ultimoRegistro());
        r.setVencimentoRegistro(dataVencimento);
        return r;
    }

    public static RegistroResponse toResponse(RegistroFinanceiro r){
        return  new RegistroResponse(r.getMotivo(),r.getValor());
    }
}
