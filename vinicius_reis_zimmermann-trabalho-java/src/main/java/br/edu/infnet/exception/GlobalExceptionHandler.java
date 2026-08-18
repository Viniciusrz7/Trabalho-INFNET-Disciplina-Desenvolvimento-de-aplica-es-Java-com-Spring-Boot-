package br.edu.infnet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErroResponse> criarResposta(HttpStatus status, String mensagem){
        ErroResponse erro = new ErroResponse(status.value(),
                status.getReasonPhrase(),
                mensagem,
                LocalDateTime.now());
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException exception){
       return criarResposta(HttpStatus.NOT_FOUND,exception.getMessage());
    }

    @ExceptionHandler(IdentificadorDuplicadoException.class)
    public ResponseEntity<ErroResponse> tratarIdentificadorDuplicadoException(IdentificadorDuplicadoException exception){
        return criarResposta(HttpStatus.CONFLICT,exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> tratarRecursoArgumentoInvalido(IllegalArgumentException exception){
        return criarResposta(HttpStatus.BAD_REQUEST,exception.getMessage());
    }

}
