package com.projects.controle_financeiro.application.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntidadeBadRequest extends RuntimeException {
    public EntidadeBadRequest(String message) {
        super(message);
    }
}
