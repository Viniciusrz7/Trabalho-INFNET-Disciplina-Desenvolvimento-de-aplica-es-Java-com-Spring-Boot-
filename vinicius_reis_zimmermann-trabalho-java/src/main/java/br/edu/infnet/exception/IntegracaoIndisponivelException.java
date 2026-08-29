package br.edu.infnet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class IntegracaoIndisponivelException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public IntegracaoIndisponivelException(String mensagem){
        super(mensagem);
    }
}
