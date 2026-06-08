package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.Atraso;

import java.util.List;
import java.util.Optional;

public interface AtrasoPort {
    void registrarAtraso(Atraso atraso);
    List<Atraso> listAllAtrasos();
    Optional<Atraso> listByIdAtraso(Long id);
}
