package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.conta_bancaria.ContaBancariaRequest;
import com.projects.controle_financeiro.adapter.in.dto.despesa.DespesaRequest;
import com.projects.controle_financeiro.adapter.in.dto.mapper.ContaBancariaMapper;
import com.projects.controle_financeiro.adapter.in.dto.mapper.DespesaMapper;
import com.projects.controle_financeiro.adapter.in.dto.mapper.MovimentacaoMapper;
import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoRequest;
import com.projects.controle_financeiro.application.domain.enums.StatusMovimentacao;
import com.projects.controle_financeiro.application.domain.enums.TipoConta;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeBadRequestException;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFoundException;
import com.projects.controle_financeiro.application.domain.exceptions.RegraNegocioException;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Despesa;
import com.projects.controle_financeiro.application.domain.model.Movimentacao;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.domain.strategy.MovimentacaoStrategy;
import com.projects.controle_financeiro.application.port.in.RegistroEntidadesUseCase;
import com.projects.controle_financeiro.application.port.out.ContaBancariaPort;
import com.projects.controle_financeiro.application.port.out.DespesaPort;
import com.projects.controle_financeiro.application.port.out.MovimentacaoPort;
import com.projects.controle_financeiro.application.port.out.UsuarioPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RegistroEntidadesService implements RegistroEntidadesUseCase {
    private final UsuarioPort usuarioPort;
    private final ContaBancariaPort contaBancariaPort;
    private final MovimentacaoPort movimentacaoPort;
    private final DespesaPort despesaPort;
    private final List<MovimentacaoStrategy> strategies;

    public RegistroEntidadesService(UsuarioPort usuarioPort, ContaBancariaPort contaBancariaPort, MovimentacaoPort movimentacaoPort, DespesaPort despesaPort, List<MovimentacaoStrategy> strategies) {
        this.usuarioPort = usuarioPort;
        this.contaBancariaPort = contaBancariaPort;
        this.movimentacaoPort = movimentacaoPort;
        this.despesaPort = despesaPort;
        this.strategies = strategies;
    }

    // USUÁRIO
    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        log.info("Iniciando Cadastro de Usuário");
        Usuario salvo = usuarioPort.addUsuario(usuario);
        log.info("Usuário Cadastrado com Sucesso");
        return salvo;
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        log.info("Iniciando Listagem de Usuários");
        List<Usuario> usuarios = usuarioPort.listAllUsuario();
        log.info("Usuários Encontrados com Sucesso");
        return usuarios;
    }

    @Override
    public Usuario listarPorIdUsuario(Long id) {
        log.info("Iniciando Busca de Usuário");
        Optional<Usuario> opt = usuarioPort.listByIdUsuario(id);
        if (opt.isEmpty()){throw new EntidadeNotFoundException("Usuário Não Encontrado");}
        log.info("Usuário Encontrado");
        return opt.get();
    }

    @Override
    public void excluirUsuario(Long id) {
        Usuario usuario = listarPorIdUsuario(id);
        log.info("Iniando Exclusão de Usuário");
        usuarioPort.delUsuario(usuario);
        log.info("Usuário Excluído");
    }

    // CONTA BANCÁRIA
    @Override
    public ContaBancaria cadastrarConta(ContaBancariaRequest request) {
        log.info("Iniciando Cadastro de Conta Bancária");
        if (!validTipoConta(request.tipoConta())){throw new EntidadeBadRequestException("Tipo da Conta Inválido");}
        ContaBancaria conta = ContaBancariaMapper.toEntity(request);
        conta.setUsuario(listarPorIdUsuario(request.usuarioId()));
        ContaBancaria salvo = contaBancariaPort.addContaBancaria(conta);
        log.info("Conta Bancária Cadastrada");
        return salvo;
    }

    @Override
    public List<ContaBancaria> listarTodasContas() {
        log.info("Iniciando Listagem de Contas Bancárias");
        List<ContaBancaria> contas = contaBancariaPort.listAllContaBancarias();
        log.info("Contas Bancárias Encontradas com Sucesso");
        return contas;
    }

    @Override
    public ContaBancaria listarPorIdConta(Long id) {
        log.info("Iniciando Busca de Conta Bancária");
        Optional<ContaBancaria> opt = contaBancariaPort.listByIdContaBancaria(id);
        if (opt.isEmpty()){throw new EntidadeNotFoundException("Conta Bancária Não Existe");}
        log.info("Conta Bancária Encontrada");
        return opt.get();
    }

    @Override
    public void excluirConta(Long id) {
        ContaBancaria conta = listarPorIdConta(id);
        log.info("Iniando Exclusão de Conta Bancária");
        contaBancariaPort.delContaBancaria(conta);
        log.info("Conta Bancária Excluída");
    }

    // MOVIMENTAÇÃO
    @Override
    public Movimentacao cadastrarMovimentacao(MovimentacaoRequest request) {
        log.info("Iniciando Cadastro de Movimentação");
        ContaBancaria conta = listarPorIdConta(request.contaId());
        Movimentacao movimentacao = MovimentacaoMapper.toEntity(request);
        MovimentacaoStrategy strategy = resolveFrom(request.tipoMovimentacao());
        if (shouldPagar(request.status())){movimentacao.setStatus(StatusMovimentacao.PAGAR.getStatus());
        }else{movimentacao.setStatus(StatusMovimentacao.AGENDAR.getStatus());}
        Double saldoFinal = strategy.movimentar(conta.getSaldo(),request.valor());
        atualizarConta(conta,saldoFinal);
        if (request.recorrente()){movimentacao.setPeriodo(request.periodo().toUpperCase());}
        movimentacao.setConta(listarPorIdConta(request.contaId()));
        Movimentacao salvo = movimentacaoPort.addMovimentacao(movimentacao);
        log.info("Movimentação Cadastrada");
        return salvo;
    }

    @Override
    public List<Movimentacao> listarTodasMovimentacoes() {
        log.info("Iniciando Listagem de Movimentações");
        List<Movimentacao> movimentacoes = movimentacaoPort.listAllMovimentacoes();
        log.info("Movimentações Encontradas com Sucesso");
        return movimentacoes;
    }

    @Override
    public Movimentacao listarPorIdMovimentacao(Long id) {
        log.info("Iniciando Busca de Movimentação");
        Optional<Movimentacao> opt = movimentacaoPort.listByIdMovimentacao(id);
        if (opt.isEmpty()){throw new EntidadeNotFoundException("Movimentação Não Encontrada");}
        log.info("Movimentação Encontrada");
        return opt.get();
    }

    // DESPESA
    @Override
    public Despesa cadastrarDespesa(DespesaRequest request) {
        // ADICIONAR VALIDAÇÃO POR TIPO DE DESPESA
        Despesa despesa = DespesaMapper.toEntity(request);
        ContaBancaria conta = listarPorIdConta(request.contaId());
        despesa.setConta(conta);
        return despesaPort.addDespesa(despesa);
    }

    @Override
    public List<Despesa> listarTodasDespesas() {
        return despesaPort.listAllDespesas();
    }

    @Override
    public Despesa listarPorIdDespesa(Long id) {
        Optional<Despesa> opt = despesaPort.listByIdDespesa(id);
        if (opt.isEmpty()){throw new EntidadeNotFoundException("Despesa Não Encontrada");}
        return opt.get();
    }

    @Override
    public void excluirDespesa(Despesa despesa,Long id) {
        Despesa atualizar = listarPorIdDespesa(id);
        despesa.setId(atualizar.getId());
        despesa.setAtivo(false);
        despesaPort.addDespesa(despesa);
    }


    // -------------------------- FUNÇÕES COMPLEMENTARES --------------------------
    public Boolean validTipoConta(String tipoConta){
        return Arrays.stream(TipoConta.values()).anyMatch(tipo -> tipo.name().equals(tipoConta.toUpperCase()));
    }
    public MovimentacaoStrategy resolveFrom (String tipo){
        return strategies.stream()
                .filter(s -> s.supports(tipo))
                .findFirst()
                .orElseThrow(() -> new RegraNegocioException("Tipo inválido"));
    }
    public Boolean shouldPagar(String tipo){
        return StatusMovimentacao.PAGAR.getStatus().equals(tipo.toUpperCase());
    }
    public void atualizarConta(ContaBancaria conta, Double novoSaldo) {
        conta.setSaldo(novoSaldo);
        contaBancariaPort.uptContaBancaria(conta);
    }
}
