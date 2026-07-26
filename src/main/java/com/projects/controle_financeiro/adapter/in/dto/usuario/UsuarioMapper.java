package com.projects.controle_financeiro.adapter.in.dto.usuario;

import com.projects.controle_financeiro.application.domain.model.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioRequest request){
        Usuario u = new Usuario();
        u.setNome(request.nome());
        u.setEmail(request.email());
        u.setSenha(request.senha());
        return u;
    }
}
