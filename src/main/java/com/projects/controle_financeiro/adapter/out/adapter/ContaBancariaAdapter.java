package com.projects.controle_financeiro.adapter.out.adapter;

import com.projects.controle_financeiro.adapter.out.repository.ContaBancariaRepository;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.port.out.ContaBancariaPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContaBancariaAdapter implements ContaBancariaPort {
    private final ContaBancariaRepository repository;
    public ContaBancariaAdapter(ContaBancariaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ContaBancaria addContaBancaria(ContaBancaria conta) {
        return repository.save(conta);
    }

    @Override
    public List<ContaBancaria> listAllContaBancarias() {
        return repository.findAll();
    }

    @Override
    public Optional<ContaBancaria> listByIdContaBancaria(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delContaBancaria(ContaBancaria conta) {
        repository.delete(conta);
    }
}
