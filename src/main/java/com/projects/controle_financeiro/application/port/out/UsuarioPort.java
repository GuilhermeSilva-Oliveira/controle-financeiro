package com.projects.controle_financeiro.application.port.out;

import com.projects.controle_financeiro.application.domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UsuarioPort {
    Usuario addUsuario(Usuario usuario);
    List<Usuario> listAllUsuario();
    Optional<Usuario> listByIdUsuario(Long id);
    void delUsuario(Usuario usuario);
}
