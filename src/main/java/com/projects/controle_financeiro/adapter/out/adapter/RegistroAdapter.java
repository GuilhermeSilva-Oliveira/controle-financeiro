package com.projects.controle_financeiro.adapter.out.adapter;

import com.projects.controle_financeiro.adapter.out.repository.ContaRepository;
import com.projects.controle_financeiro.adapter.out.repository.RegistroRepository;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;
import com.projects.controle_financeiro.application.port.out.ContaPort;
import com.projects.controle_financeiro.application.port.out.RegistroPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RegistroAdapter implements RegistroPort {
    private final RegistroRepository repository;

    @Override
    public RegistroFinanceiro cadastrar(RegistroFinanceiro registro) {
        return repository.save(registro);
    }

    @Override
    public List<RegistroFinanceiro> listar() {
        return repository.findAll();
    }

    @Override
    public List<RegistroFinanceiro> listarPorTipoRegistro(String tipoRegistro) {
        return repository.findByTipoRegistro(tipoRegistro);
    }

    @Override
    public Optional<RegistroFinanceiro> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<RegistroFinanceiro> buscarPorMotivo(String motivo) {
        return repository.findByMotivo(motivo);
    }
}
