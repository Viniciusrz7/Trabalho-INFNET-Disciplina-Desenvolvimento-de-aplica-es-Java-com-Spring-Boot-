package br.edu.infnet.arquitetura.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component 
public class LanchoneteProducer {

    private final RabbitTemplate rabbitTemplate;

    public LanchoneteProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviar(LanchoneteMassage message) {
        rabbitTemplate.convertAndSend("lanchonetes", message);
        System.out.println("Mensagem enviada para a fila: " + message);
    }
    
}