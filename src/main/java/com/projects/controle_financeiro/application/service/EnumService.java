package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.conta.ContaMapper;
import com.projects.controle_financeiro.adapter.in.dto.conta.ContaRequest;
import com.projects.controle_financeiro.application.domain.enums.MotivoRegistro;
import com.projects.controle_financeiro.application.domain.enums.PeriodoRegistro;
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

import java.time.LocalDate;
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

    public static boolean validarMotivoRegistro(String motivo){
        for (MotivoRegistro motivoRegistro : MotivoRegistro.values()) {
            if (motivoRegistro.name().equals(motivo)) {
                return true;
            }} return false;
    }

    public static boolean validarPeriodoRegistro(String periodo){
        for (PeriodoRegistro periodoRegistro : PeriodoRegistro.values()) {
            if (periodoRegistro.name().equals(periodo)) {
                return true;
            }} return false;
    }

    public static LocalDate obterDataVencimento(LocalDate ultimoRegistro, String periodo){
        if (PeriodoRegistro.valueOf(periodo).equals(PeriodoRegistro.MENSAL)) {
            return ultimoRegistro.plusMonths(1);
        }else if (PeriodoRegistro.valueOf(periodo).equals(PeriodoRegistro.ANUAL)) {
            return ultimoRegistro.plusYears(1);
        }
        return ultimoRegistro.plusDays(1);
    }
}

