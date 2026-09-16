package br.edu.infnet.exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ResponseEntity<ErroResponse> criarResposta(HttpStatus status, String mensagem){
        ErroResponse erro = new ErroResponse(status.value(),
                status.getReasonPhrase(),
                mensagem,
                LocalDateTime.now());
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarErroValidacao(MethodArgumentNotValidException exception){

        String mensagem = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return criarResposta(HttpStatus.BAD_REQUEST,mensagem);
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

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErroResponse> tratarFeign(FeignException exception) {
        return criarResposta(HttpStatus.BAD_GATEWAY,"O serviço de consulta de CEP está indisponível no momento.");
    }

}
