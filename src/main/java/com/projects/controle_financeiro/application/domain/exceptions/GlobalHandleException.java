package com.projects.controle_financeiro.application.domain.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalHandleException {
    @Value("${api.base-url}")
    private String baseURL;

    @Value("api.error.bad-request")
    private String badRequest;

    @Value("api.error.not-found")
    private String notFound;

    @Value("api.error.conflict")
    private String conflict;

    @ExceptionHandler(EntidadeBadRequestException.class)
    public ProblemDetail handleEntidadeBadRequest(EntidadeBadRequestException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Entidade com Erro");
        problem.setType(URI.create(baseURL+badRequest));
        return problem;
    }

    @ExceptionHandler(EntidadeConflictException.class)
    public ProblemDetail handleEntidadeConflict(EntidadeConflictException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("Entidade com Conflito");
        problem.setType(URI.create(baseURL+conflict));
        return problem;
    }

    @ExceptionHandler(EntidadeNotFoundException.class)
    public ProblemDetail handleEntidadeNotFound(EntidadeNotFoundException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Entidade Não Encontrada");
        problem.setType(URI.create(baseURL+notFound));
        return problem;
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ProblemDetail handleRegraNegocio(RegraNegocioException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Erro na Regra de Negócio");
        problem.setType(URI.create(baseURL+badRequest));
        return problem;
    }
}
