package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.registro.RegistroMapper;
import com.projects.controle_financeiro.adapter.in.dto.registro.RegistroRequest;
import com.projects.controle_financeiro.application.domain.enums.MotivoRegistro;
import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeBadRequestException;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFoundException;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;
import com.projects.controle_financeiro.application.port.in.RegistroUseCase;
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
public class RegistroService implements RegistroUseCase {
    private final RegistroPort registroPort;
    private final ContaPort contaPort;

    @Override
    public RegistroFinanceiro cadastrar(RegistroRequest request) {
        ContaBancaria conta = contaPort.buscarPorId(request.contaId()).orElseThrow(() -> new EntidadeNotFoundException("Conta não encontrada"));
        if (!AuxiliarService.validarTipoMovimentacao(request.tipoRegistro())) {throw new EntidadeBadRequestException("Tipo de registro inválido");}
        if (!AuxiliarService.validarMotivoRegistro(request.motivo())) {throw new EntidadeBadRequestException("Motivo de registro inválido");}
        if (!AuxiliarService.validarPeriodoRegistro(request.periodo())) {throw new EntidadeBadRequestException("Período de registro inválido");}
        LocalDate dataVencimento = AuxiliarService.obterDataVencimento(request.ultimoRegistro(), request.periodo());
        if (request.tipoRegistro().equals(TipoMovimentacao.DESPESA.getTipoMovimentacao())) {
            conta.setSaldoSimulado(conta.getSaldoSimulado() - request.valor());
        }
        return registroPort.cadastrar(RegistroMapper.toEntity(request, TipoMovimentacao.valueOf(request.tipoRegistro()), MotivoRegistro.valueOf(request.motivo()), conta, dataVencimento));
    }

    @Override
    public List<RegistroFinanceiro> listar() {
        return registroPort.listar();
    }
}

