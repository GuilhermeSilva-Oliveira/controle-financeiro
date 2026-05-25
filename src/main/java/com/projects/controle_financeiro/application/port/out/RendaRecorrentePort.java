package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.RendaRecorrente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RendaRecorrentePort {
    RendaRecorrente addRendaRecorrente(RendaRecorrente renda);
    List<RendaRecorrente> listAllRendas();
    Optional<RendaRecorrente> listByIdRenda(Long id);
}
