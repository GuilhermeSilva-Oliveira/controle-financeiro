package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.AlertaAtraso;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AlertaPort {
    AlertaAtraso cadastrar(AlertaAtraso alerta);
    List<AlertaAtraso> listar();
    Optional<AlertaAtraso> buscarPorId(Integer id);
}
