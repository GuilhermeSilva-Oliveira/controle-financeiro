package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.conta.ContaMapper;
import com.projects.controle_financeiro.adapter.in.dto.conta.ContaRequest;
import com.projects.controle_financeiro.application.domain.enums.MotivoRegistro;
import com.projects.controle_financeiro.application.domain.enums.TipoConta;
import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeBadRequestException;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFoundException;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.port.in.ContaUseCase;
import com.projects.controle_financeiro.application.port.in.RotinaUseCase;
import com.projects.controle_financeiro.application.port.out.ContaPort;
import com.projects.controle_financeiro.application.port.out.RegistroPort;
import com.projects.controle_financeiro.application.port.out.UsuarioPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RotinaService implements RotinaUseCase {
    private final RegistroPort registroPort;
    private final ContaPort contaPort;

    @Override
    public void registrarReceitas() {
        List<RegistroFinanceiro> receitas = registroPort.listarPorTipoRegistro(TipoMovimentacao.RECEITA.getTipoMovimentacao());
        for (RegistroFinanceiro receita : receitas) {
            ContaBancaria conta = receita.getConta();
            if (receita.getVencimentoRegistro().equals(LocalDate.now())){
                receita.setUltimoRegistro(LocalDate.now());
                receita.setVencimentoRegistro(EnumService.obterDataVencimento(receita.getUltimoRegistro(),receita.getPeriodo()));
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

    }

    @Override
    public void alertarVencimentos() {

    }
}

