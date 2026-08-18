package br.edu.infnet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
@ResponseStatus(HttpStatus.CONFLICT)
public class IdentificadorDuplicadoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public IdentificadorDuplicadoException(String mensagem){
        super(mensagem);
    }

}
