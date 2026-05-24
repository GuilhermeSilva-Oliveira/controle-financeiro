package com.projects.controle_financeiro.adapter.in.controllers;

import com.projects.controle_financeiro.adapter.in.dto.conta_bancaria.ContaBancariaRequest;
import com.projects.controle_financeiro.adapter.in.dto.conta_bancaria.ContaBancariaResponse;
import com.projects.controle_financeiro.adapter.in.dto.mapper.ContaBancariaMapper;
import com.projects.controle_financeiro.adapter.in.dto.usuario.UsuarioRequest;
import com.projects.controle_financeiro.adapter.in.dto.usuario.UsuarioResponse;
import com.projects.controle_financeiro.adapter.in.dto.mapper.UsuarioMapper;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
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

    @PostMapping("/contas")
    public ResponseEntity<ContaBancariaResponse> cadastrarConta(@RequestBody ContaBancariaRequest request){
        return ResponseEntity.status(201).body(ContaBancariaMapper.toResponse(service.cadastrarConta(request)));
    }
}
