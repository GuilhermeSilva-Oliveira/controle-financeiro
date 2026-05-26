package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.out.repository.DespesaRepository;
import com.projects.controle_financeiro.application.domain.model.Despesa;
import com.projects.controle_financeiro.application.port.in.StatusDespesaUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StatusDespesaService implements StatusDespesaUseCase {
    private final DespesaRepository repository;
    public StatusDespesaService(DespesaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validarVencimento() {
        List<Despesa> despesas = repository.findAll();
        for (Despesa d : despesas){
            if (validarTempo(d.getDataVencimento())){
                log.info("DESPESA VENCIDA para Despesa={} na Data={}",d.getDescricao(),d.getDataVencimento());
            }
        }
    }

    // FUNÇÕES COMPLEMENTARES
    public Boolean validarTempo(LocalDateTime vencimento){
        return vencimento.isEqual(LocalDateTime.now()) || vencimento.isBefore(LocalDateTime.now());
    }
}
