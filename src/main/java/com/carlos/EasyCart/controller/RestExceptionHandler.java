package com.carlos.EasyCart.controller;

import com.carlos.EasyCart.infrastructure.dto.ErroResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResponse> handleDataIntegrity(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest()
                .body(new ErroResponse("Erro de integridade", "Dados já cadastrados no sistema."));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErroResponse> handleUserNotFound(UsernameNotFoundException ex) {
        return ResponseEntity.status(404)
                .body(new ErroResponse("Usuário não encontrado", ex.getMessage()));
    }
}