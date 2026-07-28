package com.projects.controle_financeiro.adapter.in.controllers;

import com.projects.controle_financeiro.adapter.in.dto.conta.ContaRequest;
import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoRequest;
import com.projects.controle_financeiro.adapter.in.dto.registro.RegistroRequest;
import com.projects.controle_financeiro.adapter.in.dto.usuario.UsuarioMapper;
import com.projects.controle_financeiro.adapter.in.dto.usuario.UsuarioRequest;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ----------- TODOS -------------
// TODO 1: Adicionar Validations
// TODO 2: Alerta para Movimentacões com Valor de Retirada Acima do Saldo da Conta
// TODO 3: Alerta para Prazos de Despesas Próximos
// TODO 4: Sistema de Gerenciamento de Contas Otimizado
// TODO 5: Adicionar Saldo Simulado (Com Descontos Mensais de Despesas)

@RestController
@RequestMapping("/v1/rotina")
@AllArgsConstructor
public class RotinaController {
    private final RotinaService service;

    @PostMapping("/receitas")
    public ResponseEntity<Void> verificarReceitas(){
        service.registrarReceitas();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/despesas")
    public ResponseEntity<Void> verificarDespesas(){
        service.descontarDespesas();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/vencimentos")
    public ResponseEntity<Void> verificarVencimentos(){
        service.alertarVencimentos();
        return ResponseEntity.ok().build();
    }
}
