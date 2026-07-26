package com.projects.controle_financeiro.adapter.out.adapter;

import com.projects.controle_financeiro.adapter.out.repository.ContaRepository;
import com.projects.controle_financeiro.adapter.out.repository.MovimentacaoRepository;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import com.projects.controle_financeiro.application.port.out.ContaPort;
import com.projects.controle_financeiro.application.port.out.MovimentacaoPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MovimentacaoAdapter implements MovimentacaoPort {
    private final MovimentacaoRepository repository;

    @Override
    public Movimentacao cadastrar(Movimentacao movimentacao) {
        return repository.save(movimentacao);
    }

    @Override
    public List<Movimentacao> listar() {
        return repository.findAll();
    }
}
