package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.conta_bancaria.ContaBancariaRequest;
import com.projects.controle_financeiro.adapter.in.dto.despesa.DespesaRequest;
import com.projects.controle_financeiro.adapter.in.dto.mapper.ContaBancariaMapper;
import com.projects.controle_financeiro.adapter.in.dto.mapper.DespesaMapper;
import com.projects.controle_financeiro.adapter.in.dto.mapper.MovimentacaoMapper;
import com.projects.controle_financeiro.adapter.in.dto.mapper.RendaRecorrenteMapper;
import com.projects.controle_financeiro.adapter.in.dto.movimentacao.MovimentacaoRequest;
import com.projects.controle_financeiro.adapter.in.dto.renda_recorrente.RendaRecorrenteRequest;
import com.projects.controle_financeiro.application.domain.enums.StatusMovimentacao;
import com.projects.controle_financeiro.application.domain.enums.TipoConta;
import com.projects.controle_financeiro.application.domain.enums.TipoMovimentacao;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeBadRequestException;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFoundException;
import com.projects.controle_financeiro.application.domain.exceptions.RegraNegocioException;
import com.projects.controle_financeiro.application.domain.model.*;
import com.projects.controle_financeiro.application.domain.strategy.MovimentacaoStrategy;
import com.projects.controle_financeiro.application.port.in.RegistroEntidadesUseCase;
import com.projects.controle_financeiro.application.port.out.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class RegistroEntidadesService implements RegistroEntidadesUseCase {
    private final UsuarioPort usuarioPort;
    private final ContaBancariaPort contaBancariaPort;
    private final MovimentacaoPort movimentacaoPort;
    private final DespesaPort despesaPort;
    private final RendaRecorrentePort rendaPort;
    private final List<MovimentacaoStrategy> strategies;

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
        movimentacao.setConta(listarPorIdConta(request.contaId()));
        if (request.recorrente()){movimentacao.setPeriodo(request.periodo().toUpperCase());shouldRegistrarRenda(movimentacao);}
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

    @Override
    public Movimentacao pagarDespesa(Long id, Double valor) {
        log.info("Iniciando Pagamento de Despesa");
        Despesa despesa = listarPorIdDespesa(id);
        if (validarPagamento(valor,despesa.getValor())){despesa.setDataVencimento(despesa.getDataVencimento().plusMonths(1));}
        else {throw new RegraNegocioException("Valor Pago Insuficiente");}
        Movimentacao movimentacao = gerarMovimentacao(despesa);
        log.info("Pagamento de Despesa Finalizado");
        return movimentacaoPort.addMovimentacao(movimentacao);
    }

    // DESPESA
    @Override
    public Despesa cadastrarDespesa(DespesaRequest request) {
        log.info("Iniciando Cadastro de Despesa");
        Despesa despesa = DespesaMapper.toEntity(request);
        ContaBancaria conta = listarPorIdConta(request.contaId());
        despesa.setConta(conta);
        log.info("Cadastro de Despesa Finalizado");
        return despesaPort.addDespesa(despesa);
    }

    @Override
    public List<Despesa> listarTodasDespesas() {
        log.info("Iniciando Listagem de Despesas");
        List<Despesa> despesas = despesaPort.listAllDespesas();
        log.info("Listagem de Despesas Finalizadas");
        return despesas;
    }

    @Override
    public Despesa listarPorIdDespesa(Long id) {
        log.info("Iniciando Busca de Despesa por Id");
        Optional<Despesa> opt = despesaPort.listByIdDespesa(id);
        if (opt.isEmpty()){throw new EntidadeNotFoundException("Despesa Não Encontrada");}
        log.info("Despesa Encontrada por Id");
        return opt.get();
    }

    @Override
    public void excluirDespesa(Despesa despesa,Long id) {
        log.info("Iniciando Exclusão de Despesa");
        Despesa atualizar = listarPorIdDespesa(id);
        despesa.setId(atualizar.getId());
        despesa.setAtivo(false);
        despesaPort.addDespesa(despesa);
        log.info("Despesa Excluída");
    }

    // RENDA RECORRENTE
    @Override
    public Renda cadastrarRenda(RendaRecorrenteRequest request) {
        log.info("Iniciando Cadastro de Renda");
        Renda renda = RendaRecorrenteMapper.toEntity(request);
        ContaBancaria conta = listarPorIdConta(request.contaId());
        renda.setConta(conta);
        log.info("Renda Cadastrada com Sucesso");
        return rendaPort.addRendaRecorrente(renda);
    }

    @Override
    public List<Renda> listarTodasRendas() {
        log.info("Iniciando Listagem de Rendas");
        List<Renda> rendas = rendaPort.listAllRendas();
        log.info("Listagem de Rendas Finalizada");
        return rendas;
    }

    @Override
    public Renda listarPorIdRenda(Long id) {
        log.info("Iniciando Busca por Id de Renda");
        Optional<Renda> opt = rendaPort.listByIdRenda(id);
        if (opt.isEmpty()){throw new EntidadeNotFoundException("Renda Não Encontrada");}
        log.info("Renda Encontrada");
        return opt.get();
    }

    @Override
    public void excluirRenda(Long id) {
        log.info("Iniciando Exclusão de Renda");
        Renda atualizar = listarPorIdRenda(id);
        atualizar.setAtivo(false);
        rendaPort.addRendaRecorrente(atualizar);
        log.info("Renda Excluída");
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
    public Boolean validarPagamento(Double pagamento, Double valorDespesa){
        System.out.println("Pagamento: " + pagamento);
        System.out.println("Despesa: " + valorDespesa);
        return pagamento.equals(valorDespesa);
    }

    public Movimentacao gerarMovimentacao(Despesa despesa){
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setStatus(StatusMovimentacao.PAGAR.getStatus());
        movimentacao.setDescricao(despesa.getDescricao());
        movimentacao.setPeriodo(despesa.getPeriodo());
        movimentacao.setConta(despesa.getConta());
        movimentacao.setValor(despesa.getValor());
        movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA.getStatus());
        movimentacao.setRecorrente(despesa.getAtivo());
        movimentacao.setData(LocalDateTime.now());
        Double novoSaldo = despesa.getConta().getSaldo() - despesa.getValor();
        atualizarConta(despesa.getConta(),novoSaldo);
        return movimentacao;
    }

    public void shouldRegistrarRenda(Movimentacao movimentacao){
        List<Movimentacao> movimentacoes = listarTodasMovimentacoes();
        Boolean newMovimentacao = true;
        for (Movimentacao m : movimentacoes){
            if (m.getDescricao().equals(movimentacao.getDescricao())){
                newMovimentacao = false;
            }
        }
        if (newMovimentacao){
            RendaRecorrenteRequest request = new RendaRecorrenteRequest(movimentacao.getDescricao().toUpperCase(),movimentacao.getValor(),movimentacao.getPeriodo(),movimentacao.getData(),movimentacao.getConta().getId());
            cadastrarRenda(request);
        }
    }
}
