package com.projects.controle_financeiro.adapter.in.controllers;

import com.projects.controle_financeiro.adapter.in.dto.conta.ContaRequest;
import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoRequest;
import com.projects.controle_financeiro.adapter.in.dto.registro.RegistroMapper;
import com.projects.controle_financeiro.adapter.in.dto.registro.RegistroRequest;
import com.projects.controle_financeiro.adapter.in.dto.registro.RegistroResponse;
import com.projects.controle_financeiro.adapter.in.dto.usuario.UsuarioMapper;
import com.projects.controle_financeiro.adapter.in.dto.usuario.UsuarioRequest;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import com.projects.controle_financeiro.application.domain.model.RegistroFinanceiro;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.service.ContaService;
import com.projects.controle_financeiro.application.service.MovimentacaoService;
import com.projects.controle_financeiro.application.service.RegistroService;
import com.projects.controle_financeiro.application.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// ----------- TODOS -------------
// TODO 1: Adicionar Validations
// TODO 2: Alerta para Movimentacões com Valor de Retirada Acima do Saldo da Conta
// TODO 3: Alerta para Prazos de Despesas Próximos
// TODO 4: Sistema de Gerenciamento de Contas Otimizado
// TODO 5: Adicionar Saldo Simulado (Com Descontos Mensais de Despesas)
// TODO 6: Tornar Get de Valores Dinâmico por Conta e Cliente passando Id

@RestController
@RequestMapping("/v1/frontend")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class FrontEndController {
    private final UsuarioService usuarioService;
    private final ContaService contaService;
    private final MovimentacaoService movimentacaoService;
    private final RegistroService registroService;

    @GetMapping("/renda")
    public ResponseEntity<Double> calcularRenda(){
        return ResponseEntity.ok(registroService.calcularRenda());
    }

    @GetMapping("/despesa")
    public ResponseEntity<Double> calcularDespesa(){
        return ResponseEntity.ok(registroService.calcularDespesa());
    }

    @GetMapping("/saldo")
    public ResponseEntity<Double> calcularSaldo(){
        return ResponseEntity.ok(contaService.calcularSaldo());
    }

    @GetMapping("/lista/despesa")
    public ResponseEntity<List<RegistroResponse>> listarDespesas(){
        return ResponseEntity.ok(registroService.listarDespesas().stream().map(RegistroMapper::toResponse).toList());
    }

    @GetMapping("/lista/renda")
    public ResponseEntity<List<RegistroResponse>> listarRendas(){
        return ResponseEntity.ok(registroService.listarRendas().stream().map(RegistroMapper::toResponse).toList());
    }

    @GetMapping("/usuario")
    public ResponseEntity<String> usuario(){
        return ResponseEntity.ok(usuarioService.listar().getFirst().getNome());
    }
}
