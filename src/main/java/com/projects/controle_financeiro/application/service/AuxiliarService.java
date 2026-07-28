package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.application.domain.enums.MotivoRegistro;
import com.projects.controle_financeiro.application.domain.enums.PeriodoRegistro;
import com.projects.controle_financeiro.application.domain.enums.TipoConta;
import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.model.AlertaAtraso;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
public class AuxiliarService {

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

    public static AlertaAtraso gerarAlerta(RegistroFinanceiro registro){
        AlertaAtraso a = new AlertaAtraso();
        a.setDataVencimento(registro.getVencimentoRegistro());
        a.setFinalizado(false);
        a.setRegistro(registro);
        a.setConta(registro.getConta());
        return a;
    }
}

