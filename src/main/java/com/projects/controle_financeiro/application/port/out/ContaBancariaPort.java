package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ContaBancariaPort {
    ContaBancaria addContaBancaria(ContaBancaria conta);
    List<ContaBancaria> listAllContaBancarias();
    Optional<ContaBancaria> listByIdContaBancaria(Long id);
    void delContaBancaria(ContaBancaria conta);
}
