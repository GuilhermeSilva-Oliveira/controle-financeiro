package com.projects.controle_financeiro.adapter.out.adapter;

import com.projects.controle_financeiro.adapter.out.repository.RendaRecorrenteRepository;
import com.projects.controle_financeiro.application.domain.model.RendaRecorrente;
import com.projects.controle_financeiro.application.port.out.RendaRecorrentePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RendaRecorrenteAdapter implements RendaRecorrentePort {
    private final RendaRecorrenteRepository repository;
    public RendaRecorrenteAdapter(RendaRecorrenteRepository repository) {
        this.repository = repository;
    }

    @Override
    public RendaRecorrente addRendaRecorrente(RendaRecorrente renda) {
        return repository.save(renda);
    }

    @Override
    public List<RendaRecorrente> listAllRendas() {
        return repository.findAll();
    }

    @Override
    public Optional<RendaRecorrente> listByIdRenda(Long id) {
        return repository.findById(id);
    }
}
