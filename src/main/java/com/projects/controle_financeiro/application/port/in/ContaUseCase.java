package com.projects.controle_financeiro.application.port.in;

import com.projects.controle_financeiro.adapter.in.dto.conta.ContaRequest;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContaUseCase {
    ContaBancaria cadastrar(ContaRequest request);
    List<ContaBancaria> listar();
    Double calcularSaldo();
}
