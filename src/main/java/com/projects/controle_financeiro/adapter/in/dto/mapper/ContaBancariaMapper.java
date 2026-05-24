package com.projects.controle_financeiro.adapter.in.dto.mapper;

import com.projects.controle_financeiro.adapter.in.dto.conta_bancaria.ContaBancariaRequest;
import com.projects.controle_financeiro.adapter.in.dto.conta_bancaria.ContaBancariaResponse;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;

public class ContaBancariaMapper {
    public static ContaBancaria toEntity(ContaBancariaRequest request){
        ContaBancaria conta = new ContaBancaria();
        conta.setNomeBanco(request.nomeBanco());
        conta.setSaldo(request.saldo());
        conta.setTipoConta(request.tipoConta());
        return conta;
    }

    public static ContaBancariaResponse toResponse(ContaBancaria conta){
        return new ContaBancariaResponse(conta.getId(),conta.getNomeBanco(),conta.getSaldo(),conta.getTipoConta(),conta.getUsuario());
    }
}
