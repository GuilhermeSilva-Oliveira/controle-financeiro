package com.projects.controle_financeiro.application.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeConflict extends RuntimeException {
    public EntidadeConflict(String message) {
        super(message);
    }
}
