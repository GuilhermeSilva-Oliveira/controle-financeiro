package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.mapper.AtrasoMapper;
import com.projects.controle_financeiro.adapter.out.repository.DespesaRepository;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFoundException;
import com.projects.controle_financeiro.application.domain.model.Atraso;
import com.projects.controle_financeiro.application.domain.model.Despesa;
import com.projects.controle_financeiro.application.port.in.StatusDespesaUseCase;
import com.projects.controle_financeiro.application.port.out.AtrasoPort;
import com.projects.controle_financeiro.application.port.out.DespesaPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StatusDespesaService implements StatusDespesaUseCase {
    private final DespesaPort despesaPort;
    private final AtrasoPort atrasoPort;
    public StatusDespesaService(DespesaPort despesaPort, AtrasoPort atrasoPort) {
        this.despesaPort = despesaPort;
        this.atrasoPort = atrasoPort;
    }

    @Override
    public void validarVencimento() {
        List<Despesa> despesas = despesaPort.listAllDespesas();
        for (Despesa d : despesas){
            if (validarTempo(d.getDataVencimento())){
                log.info("DESPESA VENCIDA para Despesa={} na Data={}",d.getDescricao(),d.getDataVencimento());
                atrasoPort.registrarAtraso(AtrasoMapper.gerarAtraso(d));
            }
        }
    }

    // FUNÇÕES COMPLEMENTARES
    public Boolean validarTempo(LocalDateTime vencimento){
        return vencimento.isEqual(LocalDateTime.now()) || vencimento.isBefore(LocalDateTime.now());
    }
}
