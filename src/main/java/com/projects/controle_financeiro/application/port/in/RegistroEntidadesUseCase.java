package com.projects.controle_financeiro.application.port.in;

import com.projects.controle_financeiro.adapter.in.dto.conta_bancaria.ContaBancariaRequest;
import com.projects.controle_financeiro.adapter.in.dto.despesa.DespesaRequest;
import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoRequest;
import com.projects.controle_financeiro.adapter.in.dto.renda_recorrente.RendaRecorrenteRequest;
import com.projects.controle_financeiro.application.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RegistroEntidadesUseCase {
    // USUÁRIO
    Usuario cadastrarUsuario(Usuario usuario);
    List<Usuario> listarTodosUsuarios();
    Usuario listarPorIdUsuario(Long id);
    void excluirUsuario(Long id);

    // CONTA BANCÁRIA
    ContaBancaria cadastrarConta(ContaBancariaRequest request);
    List<ContaBancaria> listarTodasContas();
    ContaBancaria listarPorIdConta(Long id);
    void excluirConta(Long id);

    // MOVIMENTAÇÃO
    Movimentacao cadastrarMovimentacao(MovimentacaoRequest request);
    List<Movimentacao> listarTodasMovimentacoes();
    Movimentacao listarPorIdMovimentacao(Long id);
    Movimentacao pagarDespesa(Long id,Double valor);

    // DESPESA
    Despesa cadastrarDespesa(DespesaRequest request);
    List<Despesa> listarTodasDespesas();
    Despesa listarPorIdDespesa(Long id);
    void excluirDespesa(Despesa despesa,Long id);

    // RENDA RECORRENTE
    Renda cadastrarRenda(RendaRecorrenteRequest request);
    List<Renda> listarTodasRendas();
    Renda listarPorIdRenda(Long id);
    void excluirRenda(Long id);
}
