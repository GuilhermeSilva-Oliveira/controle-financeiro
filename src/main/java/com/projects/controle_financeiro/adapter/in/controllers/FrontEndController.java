package com.projects.controle_financeiro.adapter.in.controllers;

import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoControleResponse;
import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoMapper;
import com.projects.controle_financeiro.adapter.in.dto.registro.DespesaControleResponse;
import com.projects.controle_financeiro.adapter.in.dto.registro.ReceitaControleResponse;
import com.projects.controle_financeiro.adapter.in.dto.registro.RegistroMapper;
import com.projects.controle_financeiro.adapter.in.dto.registro.RegistroResponse;
import com.projects.controle_financeiro.application.service.ContaService;
import com.projects.controle_financeiro.application.service.MovimentacaoService;
import com.projects.controle_financeiro.application.service.RegistroService;
import com.projects.controle_financeiro.application.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/lista/controle/movimentacoes")
    public ResponseEntity<List<MovimentacaoControleResponse>> listarControleMovimentacoes(){
        return ResponseEntity.ok(movimentacaoService.listar().stream().map(MovimentacaoMapper::toResponseControle).toList());
    }

    @GetMapping("/lista/controle/rendas")
    public ResponseEntity<List<ReceitaControleResponse>> listarControleRendas(){
        return ResponseEntity.ok(registroService.listarRendas().stream().map(RegistroMapper::toReceitaControle).toList());
    }

    @GetMapping("/lista/controle/despesas")
    public ResponseEntity<List<DespesaControleResponse>> listarControleDespesas(){
        return ResponseEntity.ok(registroService.listarDespesas().stream().map(RegistroMapper::toDespesaControle).toList());
    }
}
