package com.projects.controle_financeiro.adapter.out.adapter;

import com.projects.controle_financeiro.adapter.out.repository.DespesaRepository;
import com.projects.controle_financeiro.application.domain.model.Despesa;
import com.projects.controle_financeiro.application.port.out.DespesaPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DespesaAdapter implements DespesaPort {
    private final DespesaRepository repository;
    public DespesaAdapter(DespesaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Despesa addDespesa(Despesa despesa) {
        return repository.save(despesa);
    }

    @Override
    public List<Despesa> listAllDespesas() {
        return repository.findAll();
    }

    @Override
    public Optional<Despesa> listByIdDespesa(Long id) {
        return repository.findById(id);
    }
}
