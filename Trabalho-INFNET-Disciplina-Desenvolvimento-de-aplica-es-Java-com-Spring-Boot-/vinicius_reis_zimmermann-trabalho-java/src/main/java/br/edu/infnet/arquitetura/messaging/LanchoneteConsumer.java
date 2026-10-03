package br.edu.infnet.arquitetura.messaging;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LanchoneteConsumer {

    @RabbitListener(queues = "lanchonetes")
    public void receber(LanchoneteMassage message) {
        System.out.println("Mensagem recebida da fila: " + message);
    }

}