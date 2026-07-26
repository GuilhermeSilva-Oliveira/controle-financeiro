package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.port.in.UsuarioUseCase;
import com.projects.controle_financeiro.application.port.out.UsuarioPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UsuarioService implements UsuarioUseCase {
    private final UsuarioPort usuarioPort;

    @Override
    public Usuario cadastrar(Usuario usuario) {
        return usuarioPort.cadastrar(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioPort.listar();
    }
}

