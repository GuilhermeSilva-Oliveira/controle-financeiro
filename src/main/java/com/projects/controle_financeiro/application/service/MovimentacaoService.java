package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoMapper;
import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoRequest;
import com.projects.controle_financeiro.adapter.in.dto.registro.MotivoControleResponse;
import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeBadRequestException;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFoundException;
import com.projects.controle_financeiro.application.domain.model.AlertaAtraso;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;
import com.projects.controle_financeiro.application.port.in.MovimentacaoUseCase;
import com.projects.controle_financeiro.application.port.out.AlertaPort;
import com.projects.controle_financeiro.application.port.out.ContaPort;
import com.projects.controle_financeiro.application.port.out.MovimentacaoPort;
import com.projects.controle_financeiro.application.port.out.RegistroPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MovimentacaoService implements MovimentacaoUseCase {
    private final MovimentacaoPort movimentacaoPort;
    private final ContaPort contaPort;
    private final RegistroPort registroPort;
    private final AlertaPort alertaPort;

    @Override
    public Movimentacao cadastrar(MovimentacaoRequest request) {
        ContaBancaria conta = contaPort.buscarPorId(request.contaId()).orElseThrow(() -> new EntidadeNotFoundException("Conta não encontrada"));
        if (!AuxiliarService.validarTipoMovimentacao(request.tipoMovimentacao())) {throw new EntidadeBadRequestException("Tipo de Movimentação Não Registrado");}
        conta.setSaldo(novoSaldo(conta,TipoMovimentacao.valueOf(request.tipoMovimentacao()), request));
        contaPort.cadastrar(conta); // ATUALIZANDO CONTA
        if (request.isPagandoDespesa()){
            List<AlertaAtraso> alertas = alertaPort.listar();
            for (AlertaAtraso alerta : alertas) {
                if (alerta.getConta().getId() == request.contaId()){
                    if (alerta.getRegistro().getMotivo().equals(request.motivo())){
                        alerta.setFinalizado(true);
                    }
                }
            }
        }
        return movimentacaoPort.cadastrar(MovimentacaoMapper.toEntity(request, TipoMovimentacao.valueOf(request.tipoMovimentacao()),conta));
    }

    @Override
    public List<Movimentacao> listar() {
        return movimentacaoPort.listar();
    }

    public Double novoSaldo(ContaBancaria conta, TipoMovimentacao tipoMovimentacao, MovimentacaoRequest request) {
        return switch (tipoMovimentacao) {
            case SAIDA -> conta.getSaldo() - request.valor();
            case ENTRADA -> conta.getSaldo() + request.valor();
            default -> throw new IllegalStateException("Valor Inesperado: " + tipoMovimentacao);
        };
    }

    public void pagarDespesa(MovimentacaoRequest request) {
        RegistroFinanceiro registro = registroPort.buscarPorMotivo(request.motivo()).orElseThrow(() -> new EntidadeNotFoundException("Registro não encontrado"));
        registro.setPago(true);
        registroPort.cadastrar(registro); // ATUALIZANDO REGISTRO
    }

    @Override
    public List<MotivoControleResponse> listarMotivos() {
        List<Movimentacao> registros = movimentacaoPort.listar();
        return registros.stream()
                .collect(Collectors.groupingBy(
                        Movimentacao::getMotivo,
                        Collectors.summingDouble(Movimentacao::getValor)
                ))
                .entrySet()
                .stream()
                .map(entry -> new MotivoControleResponse(
                        entry.getKey(),
                        entry.getValue()
                ))
                .toList();
    }
}

