package com.projects.controle_financeiro.adapter.in.dto.mapper;

import com.projects.controle_financeiro.adapter.in.dto.UsuarioRequest;
import com.projects.controle_financeiro.adapter.in.dto.UsuarioResponse;
import com.projects.controle_financeiro.application.domain.model.Usuario;

import java.time.LocalDateTime;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioRequest request){
        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setDataCadastro(LocalDateTime.now());
        return usuario;
    }

    public static UsuarioResponse toResponse(Usuario usuario){
        return new UsuarioResponse(usuario.getId(),usuario.getNome(),usuario.getEmail(),usuario.getDataCadastro());
    }
}
