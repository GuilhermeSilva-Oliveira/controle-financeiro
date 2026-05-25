package com.projects.controle_financeiro.adapter.in.controllers;

import com.projects.controle_financeiro.application.service.StatusDespesaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusDespesaController {
    private final StatusDespesaService service;
    public StatusDespesaController(StatusDespesaService service) {
        this.service = service;
    }

    @PostMapping("/vencimentos")
    public void validarVencimentos(){
        service.validarVencimento();
    }

//    @PostMapping("/automatico")
//    public void debitoAutomatico(){
//
//    }
}
