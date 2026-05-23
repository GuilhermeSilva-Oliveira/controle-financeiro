package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFound;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.port.in.RegistroEntidadesUseCase;
import com.projects.controle_financeiro.application.port.out.UsuarioPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroEntidadesService implements RegistroEntidadesUseCase {
    private final UsuarioPort usuarioPort;
    public RegistroEntidadesService(UsuarioPort usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioPort.addUsuario(usuario);
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return usuarioPort.listAllUsuario();
    }

    @Override
    public Usuario listarPorIdUsuario(Long id) {
        Optional<Usuario> opt = usuarioPort.listByIdUsuario(id);
        if (opt.isEmpty()){throw new EntidadeNotFound("Usuário Não Encontrado");}
        return opt.get();
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        usuarioPort.delUsuario(usuario);
    }
}
