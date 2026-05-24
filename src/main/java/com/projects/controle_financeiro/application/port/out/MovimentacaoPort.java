package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovimentacaoPort {
    Movimentacao addMovimentacao(Movimentacao movimentacao);
    List<Movimentacao> listAllMovimentacoes();
    Optional<Movimentacao> listByIdMovimentacao(Long id);
    void delMovimentacao(Movimentacao movimentacao);
}
