package br.edu.infnet.entrega.client.exception;

public class EntregaRemotoNaoEncontradoException extends RuntimeException{

    public EntregaRemotoNaoEncontradoException(Long entregaId) {
        super("O aluno de ID " + entregaId +  "não foi encontrado no entrega-service");

    }
}
