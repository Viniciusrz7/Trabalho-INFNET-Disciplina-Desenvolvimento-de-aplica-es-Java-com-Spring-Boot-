package br.edu.infnet.exception;

import java.io.Serial;

public class IdentificadorDuplicadoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public IdentificadorDuplicadoException(String mensagem){
        super(mensagem);
    }

}
