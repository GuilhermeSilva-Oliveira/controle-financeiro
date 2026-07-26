package com.projects.controle_financeiro.application.service;

import com.projects.controle_financeiro.adapter.in.dto.conta.ContaMapper;
import com.projects.controle_financeiro.adapter.in.dto.conta.ContaRequest;
import com.projects.controle_financeiro.application.domain.enums.TipoConta;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeBadRequestException;
import com.projects.controle_financeiro.application.domain.exceptions.EntidadeNotFoundException;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import com.projects.controle_financeiro.application.domain.model.Usuario;
import com.projects.controle_financeiro.application.port.in.ContaUseCase;
import com.projects.controle_financeiro.application.port.in.UsuarioUseCase;
import com.projects.controle_financeiro.application.port.out.ContaPort;
import com.projects.controle_financeiro.application.port.out.UsuarioPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ContaService implements ContaUseCase {
    private final ContaPort contaPort;
    private final UsuarioPort usuarioPort;

    @Override
    public ContaBancaria cadastrar(ContaRequest request) {
        Usuario usuario = usuarioPort.buscarPorId(request.usuarioId()).orElseThrow(() -> new EntidadeNotFoundException("Usuário não encontrado"));
        if (!validarTipoConta(request.tipoConta())) {throw new EntidadeBadRequestException("Tipo de Conta Não Registrado");}
        return contaPort.cadastrar(ContaMapper.toEntity(request,TipoConta.valueOf(request.tipoConta()),usuario));
    }

    @Override
    public List<ContaBancaria> listar() {
        return contaPort.listar();
    }

    public boolean validarTipoConta(String tipo){
        for (TipoConta tipoConta : TipoConta.values()) {
            if (tipoConta.name().equals(tipo)) {
                return true;
            }} return false;
    }
}

