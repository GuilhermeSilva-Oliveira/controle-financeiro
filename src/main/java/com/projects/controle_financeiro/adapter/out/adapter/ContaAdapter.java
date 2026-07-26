package com.projects.controle_financeiro.adapter.out.adapter;

import com.projects.controle_financeiro.adapter.out.repository.ContaRepository;
import com.projects.controle_financeiro.adapter.out.repository.UsuarioRepository;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.port.out.ContaPort;
import com.projects.controle_financeiro.application.port.out.UsuarioPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ContaAdapter implements ContaPort {
    private final ContaRepository repository;

    @Override
    public ContaBancaria cadastrar(ContaBancaria conta) {
        return repository.save(conta);
    }

    @Override
    public List<ContaBancaria> listar() {
        return repository.findAll();
    }
}
