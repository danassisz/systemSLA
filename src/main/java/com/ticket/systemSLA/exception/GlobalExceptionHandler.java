package com.ticket.systemSLA.exception;

import com.ticket.systemSLA.dto.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ResponseEntity<ErroResponse> naoEncontrado(RecursoNaoEncontrado ex) {
        return montar(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(TransicaoInvalida.class)
    public ResponseEntity<ErroResponse> transicaoInvalida(TransicaoInvalida ex) {
        return montar(HttpStatus.CONFLICT, ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> validacao(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> campos.put(e.getField(), e.getDefaultMessage()));
        return montar(HttpStatus.BAD_REQUEST, "Dados inválidos", campos);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> corpoInvalido(HttpMessageNotReadableException ex) {
        return montar(HttpStatus.BAD_REQUEST, "Corpo da requisição ausente ou inválido", null);
    }

    private ResponseEntity<ErroResponse> montar(HttpStatus status, String mensagem,
                                                Map<String, String> campos) {
        return ResponseEntity.status(status)
                .body(new ErroResponse(status.value(), mensagem, LocalDateTime.now(), campos));
    }
}