package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ContaPort {
    ContaBancaria cadastrar(ContaBancaria conta);
    List<ContaBancaria> listar();
    Optional<ContaBancaria> buscarPorId(Integer id);
}
