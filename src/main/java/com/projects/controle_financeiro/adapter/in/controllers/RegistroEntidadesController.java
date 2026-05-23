package com.projects.controle_financeiro.adapter.in.controllers;

import com.projects.controle_financeiro.adapter.in.dto.UsuarioRequest;
import com.projects.controle_financeiro.adapter.in.dto.UsuarioResponse;
import com.projects.controle_financeiro.adapter.in.dto.mapper.UsuarioMapper;
import com.projects.controle_financeiro.application.service.RegistroEntidadesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registros")
public class RegistroEntidadesController {
    private final RegistroEntidadesService service;
    public RegistroEntidadesController(RegistroEntidadesService service) {
        this.service = service;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest request){
        return ResponseEntity.status(201).body(UsuarioMapper.toResponse(service.cadastrarUsuario(UsuarioMapper.toEntity(request))));
    }
}
