package com.projects.controle_financeiro.application.port.in;

import com.projects.controle_financeiro.application.domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RegistroEntidadesUseCase {
    // USUÁRIO
    Usuario cadastrarUsuario(Usuario usuario);
    List<Usuario> listarTodosUsuarios();
    Usuario listarPorIdUsuario(Long id);
    void excluirUsuario(Usuario usuario);


}
