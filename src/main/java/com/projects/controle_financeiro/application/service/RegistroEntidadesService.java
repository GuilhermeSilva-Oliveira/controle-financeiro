package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.conta_bancaria.ContaBancariaRequest;
import com.projects.controle_financeiro.adapter.in.dto.mapper.ContaBancariaMapper;
import com.projects.controle_financeiro.application.domain.enums.TipoConta;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeBadRequest;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFound;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.port.in.RegistroEntidadesUseCase;
import com.projects.controle_financeiro.application.port.out.ContaBancariaPort;
import com.projects.controle_financeiro.application.port.out.UsuarioPort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroEntidadesService implements RegistroEntidadesUseCase {
    private final UsuarioPort usuarioPort;
    private final ContaBancariaPort contaBancariaPort;
    public RegistroEntidadesService(UsuarioPort usuarioPort, ContaBancariaPort contaBancariaPort) {
        this.usuarioPort = usuarioPort;
        this.contaBancariaPort = contaBancariaPort;
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

    // FUNÇÕES COMPLEMENTARES
    public Boolean validTipoConta(String tipoConta){
        return Arrays.stream(TipoConta.values()).anyMatch(tipo -> tipo.name().equals(tipoConta.toUpperCase()));
    }
}
