package com.projects.controle_financeiro.adapter.out.adapter;

import com.projects.controle_financeiro.adapter.out.repository.AlertaRepository;
import com.projects.controle_financeiro.adapter.out.repository.ContaRepository;
import com.projects.controle_financeiro.application.domain.model.AlertaAtraso;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.port.out.AlertaPort;
import com.projects.controle_financeiro.application.port.out.ContaPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AlertaAdapter implements AlertaPort {
    private final AlertaRepository repository;

    @Override
    public AlertaAtraso cadastrar(AlertaAtraso alerta) {
        return repository.save(alerta);
    }

    @Override
    public List<AlertaAtraso> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<AlertaAtraso> buscarPorId(Integer id) {
        return repository.findById(id);
    }
}
