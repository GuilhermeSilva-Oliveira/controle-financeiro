package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RegistroPort {
    RegistroFinanceiro cadastrar(RegistroFinanceiro registro);
    List<RegistroFinanceiro> listar();
    Optional<RegistroFinanceiro> buscarPorId(Integer id);
    Optional<RegistroFinanceiro> buscarPorMotivo(String motivo);
}
