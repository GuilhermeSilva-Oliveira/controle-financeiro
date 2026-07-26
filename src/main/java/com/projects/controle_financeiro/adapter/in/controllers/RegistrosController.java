package com.projects.controle_financeiro.adapter.in.controllers;

import com.projects.controle_financeiro.adapter.in.dto.usuario.UsuarioMapper;
import com.projects.controle_financeiro.adapter.in.dto.usuario.UsuarioRequest;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ----------- TODOS -------------
// TODO 1: Adicionar Validations

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class RegistrosController {
    private final UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioRequest request){
        return ResponseEntity.ok(usuarioService.cadastrar(UsuarioMapper.toEntity(request)));
    }
}
