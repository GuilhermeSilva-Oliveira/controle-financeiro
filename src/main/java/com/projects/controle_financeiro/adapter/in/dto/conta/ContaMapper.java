package com.projects.controle_financeiro.adapter.in.dto.conta;

import com.projects.controle_financeiro.adapter.in.dto.usuario.UsuarioRequest;
import com.projects.controle_financeiro.application.domain.enums.TipoConta;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Usuario;

public class ContaMapper {
    public static ContaBancaria toEntity(ContaRequest request, TipoConta tipo, Usuario usuario){
        ContaBancaria c = new ContaBancaria();
        c.setNomeBanco(request.nomeBanco());
        c.setSaldo(request.saldo());
        c.setTipoConta(tipo.getTipoConta());
        c.setUsuario(usuario);
        return c;
    }
}
