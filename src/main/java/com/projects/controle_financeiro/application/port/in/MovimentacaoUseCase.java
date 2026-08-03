package com.projects.controle_financeiro.application.port.in;

import com.projects.controle_financeiro.adapter.in.dto.conta.ContaRequest;
import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoRequest;
import com.projects.controle_financeiro.adapter.in.dto.registro.MotivoControleResponse;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MovimentacaoUseCase {
    Movimentacao cadastrar(MovimentacaoRequest request);
    List<Movimentacao> listar();
    List<MotivoControleResponse> listarMotivos();
}
