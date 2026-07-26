package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MovimentacaoPort {
    Movimentacao cadastrar(Movimentacao movimentacao);
    List<Movimentacao> listar();
}
