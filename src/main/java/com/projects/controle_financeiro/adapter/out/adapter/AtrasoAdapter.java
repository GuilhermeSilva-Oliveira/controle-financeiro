package com.projects.controle_financeiro.adapter.out.adapter;

import com.projects.controle_financeiro.adapter.out.repository.AtrasoRepository;
import com.projects.controle_financeiro.application.domain.model.Atraso;
import com.projects.controle_financeiro.application.port.out.AtrasoPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AtrasoAdapter implements AtrasoPort {
    private final AtrasoRepository repository;
    public AtrasoAdapter(AtrasoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrarAtraso(Atraso atraso) {
        repository.save(atraso);
    }

    @Override
    public List<Atraso> listAllAtrasos() {
        return repository.findAll();
    }

    @Override
    public Optional<Atraso> listByIdAtraso(Long id) {
        return repository.findById(id);
    }
}
