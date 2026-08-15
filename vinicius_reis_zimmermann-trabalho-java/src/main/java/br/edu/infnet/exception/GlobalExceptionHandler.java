package br.edu.infnet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Void> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IdentificadorDuplicadoException.class)
    public ResponseEntity<Void> tratarIdentificadorDuplicadoException(IdentificadorDuplicadoException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> tratarRecursoArgumentoInvalido(IllegalArgumentException exception){
        return ResponseEntity.badRequest().build();
    }

}
