package com.projects.controle_financeiro.application.port.in;

import com.projects.controle_financeiro.application.domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UsuarioUseCase {
    Usuario cadastrar(Usuario usuario);
    List<Usuario> listar();
}
