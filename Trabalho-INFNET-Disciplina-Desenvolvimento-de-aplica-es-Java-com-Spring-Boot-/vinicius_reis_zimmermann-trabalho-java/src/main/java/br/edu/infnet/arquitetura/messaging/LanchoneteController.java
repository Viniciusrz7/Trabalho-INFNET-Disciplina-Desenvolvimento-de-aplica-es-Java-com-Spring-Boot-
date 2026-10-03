package br.edu.infnet.arquitetura.messaging;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("lanchoneteMessagingController")
@RequestMapping("/mensagens/lanchonetes")
public class LanchoneteController {

    private final LanchoneteProducer lanchoneteProducer;

    public LanchoneteController(LanchoneteProducer lanchoneteProducer) {
        this.lanchoneteProducer = lanchoneteProducer;
    }

    @PostMapping 
    public void enviar(@RequestBody LanchoneteMassage messagem) {
        lanchoneteProducer.enviar(messagem);
    }

}
