package com.projects.controle_financeiro.adapter.out.adapter;

import com.projects.controle_financeiro.adapter.out.repository.MovimentacaoRepository;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import com.projects.controle_financeiro.application.port.out.MovimentacaoPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovimentacaoAdapter implements MovimentacaoPort {
    private final MovimentacaoRepository repository;
    public MovimentacaoAdapter(MovimentacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movimentacao addMovimentacao(Movimentacao movimentacao) {
        return repository.save(movimentacao);
    }

    @Override
    public List<Movimentacao> listAllMovimentacoes() {
        return repository.findAll();
    }

    @Override
    public Optional<Movimentacao> listByIdMovimentacao(Long id) {
        return repository.findById(id);
    }
}
