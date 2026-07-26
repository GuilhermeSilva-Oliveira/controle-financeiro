package com.projects.controle_financeiro.adapter.out.adapter;

import com.projects.controle_financeiro.adapter.out.repository.UsuarioRepository;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.port.out.UsuarioPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UsuarioAdapter implements UsuarioPort {
    private final UsuarioRepository repository;

    @Override
    public Usuario cadastrar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return repository.findAll();
    }
}
