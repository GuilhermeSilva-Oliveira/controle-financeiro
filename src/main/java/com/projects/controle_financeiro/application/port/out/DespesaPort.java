package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.Despesa;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DespesaPort {
    Despesa addDespesa(Despesa despesa);
    List<Despesa> listAllDespesas();
    Optional<Despesa> listByIdDespesa(Long id);
    void delDespesa(Despesa despesa);
}
