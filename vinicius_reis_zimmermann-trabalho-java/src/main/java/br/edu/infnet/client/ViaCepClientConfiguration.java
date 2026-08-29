package br.edu.infnet.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class ViaCepClientConfiguration {

    @Bean
    public ErrorDecoder viaCepErrorDecoder() {
        return new ViaCepErrorDecoder();
    }
}
