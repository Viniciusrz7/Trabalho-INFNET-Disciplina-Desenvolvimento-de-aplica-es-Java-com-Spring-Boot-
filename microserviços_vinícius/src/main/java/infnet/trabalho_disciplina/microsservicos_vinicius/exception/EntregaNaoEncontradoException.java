package infnet.trabalho_disciplina.microsservicos_vinicius.exception;

public class EntregaNaoEncontradoException extends RuntimeException{

    public EntregaNaoEncontradoException(Long id){
        super("Entrega não encontrada. ID: " + id);
    }
}
