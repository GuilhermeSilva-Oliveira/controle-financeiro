package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.conta_bancaria.ContaBancariaRequest;
import com.projects.controle_financeiro.adapter.in.dto.mapper.ContaBancariaMapper;
import com.projects.controle_financeiro.adapter.in.dto.mapper.MovimentacaoMapper;
import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoRequest;
import com.projects.controle_financeiro.application.domain.enums.StatusMovimentacao;
import com.projects.controle_financeiro.application.domain.enums.TipoConta;
import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeBadRequest;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFound;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.port.in.RegistroEntidadesUseCase;
import com.projects.controle_financeiro.application.port.out.ContaBancariaPort;
import com.projects.controle_financeiro.application.port.out.MovimentacaoPort;
import com.projects.controle_financeiro.application.port.out.UsuarioPort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroEntidadesService implements RegistroEntidadesUseCase {
    private final UsuarioPort usuarioPort;
    private final ContaBancariaPort contaBancariaPort;
    private final MovimentacaoPort movimentacaoPort;

    public RegistroEntidadesService(UsuarioPort usuarioPort, ContaBancariaPort contaBancariaPort, MovimentacaoPort movimentacaoPort) {
        this.usuarioPort = usuarioPort;
        this.contaBancariaPort = contaBancariaPort;
        this.movimentacaoPort = movimentacaoPort;
    }

    // USUÁRIO
    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioPort.addUsuario(usuario);
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return usuarioPort.listAllUsuario();
    }

    @Override
    public Usuario listarPorIdUsuario(Long id) {
        Optional<Usuario> opt = usuarioPort.listByIdUsuario(id);
        if (opt.isEmpty()){throw new EntidadeNotFound("Usuário Não Encontrado");}
        return opt.get();
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        usuarioPort.delUsuario(usuario);
    }

    // CONTA BANCÁRIA
    @Override
    public ContaBancaria cadastrarConta(ContaBancariaRequest request) {
        if (!validTipoConta(request.tipoConta())){throw new EntidadeBadRequest("Tipo da Conta Inválido");}
        ContaBancaria conta = ContaBancariaMapper.toEntity(request);
        conta.setUsuario(listarPorIdUsuario(request.usuarioId()));
        return contaBancariaPort.addContaBancaria(conta);
    }

    @Override
    public List<ContaBancaria> listarTodasContas() {
        return contaBancariaPort.listAllContaBancarias();
    }

    @Override
    public ContaBancaria listarPorIdConta(Long id) {
        Optional<ContaBancaria> opt = contaBancariaPort.listByIdContaBancaria(id);
        if (opt.isEmpty()){throw new EntidadeNotFound("Conta Bancária Não Existe");}
        return opt.get();
    }

    @Override
    public void excluirConta(ContaBancaria conta) {
        contaBancariaPort.delContaBancaria(conta);
    }

    // MOVIMENTAÇÃO
    @Override
    public Movimentacao cadastrarMovimentacao(MovimentacaoRequest request) {
        ContaBancaria conta = listarPorIdConta(request.contaId());
        Movimentacao movimentacao = MovimentacaoMapper.toEntity(request);

        // COLOCAR STRATEGY
        if (TipoMovimentacao.RECEITA.getStatus().equals(request.tipoMovimentacao().toUpperCase())){
            if (StatusMovimentacao.AGENDAR.getStatus().equals(request.status().toUpperCase())){
                movimentacao.setStatus(StatusMovimentacao.AGENDAR.getStatus());
            }else {
                Double saldoFinal = conta.getSaldo() + request.valor();
                atualizarConta(conta,saldoFinal);
                movimentacao.setStatus(StatusMovimentacao.PAGAR.getStatus());
            }
        }else if (TipoMovimentacao.DESPESA.getStatus().equals(request.tipoMovimentacao().toUpperCase())){
            if (StatusMovimentacao.AGENDAR.getStatus().equals(request.status().toUpperCase())){
                movimentacao.setStatus(StatusMovimentacao.AGENDAR.getStatus());
            }else{
                if (conta.getSaldo() < request.valor()){throw new EntidadeBadRequest("Valor da Conta Insuficiente");}
                Double saldoFinal = conta.getSaldo() - request.valor();
                atualizarConta(conta,saldoFinal);
                movimentacao.setStatus(StatusMovimentacao.PAGAR.getStatus());
            }
        }else {throw new EntidadeBadRequest("Tipo de Movimentação Inválido");}

        if (request.recorrente()){
            movimentacao.setPeriodo(request.periodo().toUpperCase());
        }
        movimentacao.setConta(listarPorIdConta(request.contaId()));
        return movimentacaoPort.addMovimentacao(movimentacao);
    }

    @Override
    public void atualizarConta(ContaBancaria conta, Double novoSaldo) {
        conta.setSaldo(novoSaldo);
        contaBancariaPort.uptContaBancaria(conta);
    }

    @Override
    public List<Movimentacao> listarTodasMovimentacoes() {
        return movimentacaoPort.listAllMovimentacoes();
    }

    @Override
    public Movimentacao listarPorIdMovimentacao(Long id) {
        Optional<Movimentacao> opt = movimentacaoPort.listByIdMovimentacao(id);
        if (opt.isEmpty()){throw new EntidadeNotFound("Movimentação Não Encontrada");}
        return opt.get();
    }

    @Override
    public void excluirMovimentacao(Movimentacao movimentacao) {
        movimentacaoPort.delMovimentacao(movimentacao);
    }

    // FUNÇÕES COMPLEMENTARES
    public Boolean validTipoConta(String tipoConta){
        return Arrays.stream(TipoConta.values()).anyMatch(tipo -> tipo.name().equals(tipoConta.toUpperCase()));
    }
}
