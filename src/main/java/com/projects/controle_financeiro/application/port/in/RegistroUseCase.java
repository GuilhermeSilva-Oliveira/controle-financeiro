package com.projects.controle_financeiro.application.port.in;

import com.projects.controle_financeiro.adapter.in.dto.conta.ContaRequest;
import com.projects.controle_financeiro.adapter.in.dto.registro.RegistroRequest;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RegistroUseCase {
    RegistroFinanceiro cadastrar(RegistroRequest request);
    List<RegistroFinanceiro> listar();
    Double calcularRenda();
    Double calcularDespesa();
    List<RegistroFinanceiro> listarDespesas();
    List<RegistroFinanceiro> listarRendas();
}
