package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.conta.ContaMapper;
import com.projects.controle_financeiro.adapter.in.dto.conta.ContaRequest;
import com.projects.controle_financeiro.application.domain.enums.TipoConta;
import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeBadRequestException;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFoundException;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.port.in.ContaUseCase;
import com.projects.controle_financeiro.application.port.out.ContaPort;
import com.projects.controle_financeiro.application.port.out.UsuarioPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class EnumService {
    public static boolean validarTipoConta(String tipo){
        for (TipoConta tipoConta : TipoConta.values()) {
            if (tipoConta.name().equals(tipo)) {
                return true;
            }} return false;
    }

    public static boolean validarTipoMovimentacao(String tipo){
        for (TipoMovimentacao tipoMovimentacao : TipoMovimentacao.values()) {
            if (tipoMovimentacao.name().equals(tipo)) {
                return true;
            }} return false;
    }
}

