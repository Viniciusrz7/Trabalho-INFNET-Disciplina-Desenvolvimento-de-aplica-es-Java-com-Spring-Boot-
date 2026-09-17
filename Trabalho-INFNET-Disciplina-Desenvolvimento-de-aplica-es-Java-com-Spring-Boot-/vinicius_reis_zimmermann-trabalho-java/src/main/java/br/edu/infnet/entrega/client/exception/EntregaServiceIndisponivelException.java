package br.edu.infnet.entrega.client.exception;

public class EntregaServiceIndisponivelException extends RuntimeException{

    public EntregaServiceIndisponivelException() {
        super("Não foi possível acessar o entrega-service");
    }

}
