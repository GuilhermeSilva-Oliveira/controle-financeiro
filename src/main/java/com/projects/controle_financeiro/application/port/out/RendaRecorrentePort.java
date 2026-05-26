package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.Renda;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RendaRecorrentePort {
    Renda addRendaRecorrente(Renda renda);
    List<Renda> listAllRendas();
    Optional<Renda> listByIdRenda(Long id);
}
