package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;
import com.projects.controle_financeiro.application.port.in.RotinaUseCase;
import com.projects.controle_financeiro.application.port.out.AlertaPort;
import com.projects.controle_financeiro.application.port.out.ContaPort;
import com.projects.controle_financeiro.application.port.out.RegistroPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RotinaService implements RotinaUseCase {
    private final RegistroPort registroPort;
    private final ContaPort contaPort;
    private final AlertaPort alertaPort;

    @Override
    public void registrarReceitas() {
        List<RegistroFinanceiro> receitas = registroPort.listarPorTipoRegistro(TipoMovimentacao.RECEITA.getTipoMovimentacao());
        for (RegistroFinanceiro receita : receitas) {
            ContaBancaria conta = receita.getConta();
            if (receita.getVencimentoRegistro().equals(LocalDate.now())){
                receita.setUltimoRegistro(LocalDate.now());
                receita.setVencimentoRegistro(AuxiliarService.obterDataVencimento(receita.getUltimoRegistro(),receita.getPeriodo()));
                receita.setPago(true);
                conta.setSaldo(conta.getSaldo() + receita.getValor());
                conta.setSaldoSimulado(conta.getSaldoSimulado() + receita.getValor());
                registroPort.cadastrar(receita);
                contaPort.cadastrar(conta);
            }
        }
    }

    @Override
    public void descontarDespesas() {
        List<RegistroFinanceiro> despesas = registroPort.listarPorTipoRegistro(TipoMovimentacao.DESPESA.getTipoMovimentacao());
        for (RegistroFinanceiro despesa : despesas) {
            if (despesa.getVencimentoRegistro().equals(LocalDate.now()) && !despesa.getPago()){
                alertaPort.cadastrar(AuxiliarService.gerarAlerta(despesa));
            }
        }
    }

    @Override
    public void alertarVencimentos() {

    }
}

