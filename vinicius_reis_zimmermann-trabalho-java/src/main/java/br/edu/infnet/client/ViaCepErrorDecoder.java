package br.edu.infnet.client;

import br.edu.infnet.exception.IntegracaoIndisponivelException;
import br.edu.infnet.exception.RecursoNaoEncontradoException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViaCepErrorDecoder implements ErrorDecoder {

    private static final Logger logger = LoggerFactory.getLogger(ViaCepErrorDecoder.class);

    @Override
    public Exception decode(String metodo, Response resposta) {

        logger.error("A consulta de CEP retornou o status {} para a requisição {}.", resposta.status(), metodo);

        if (resposta.status() == 404) {
            return new RecursoNaoEncontradoException("Nenhum endereço encontrado para o CEP informado.");
        }

        if (resposta.status() == 400) {
            return new IllegalArgumentException("O CEP informado não foi aceito pelo serviço de consulta.");
        }

        return new IntegracaoIndisponivelException("O serviço de consulta de CEP está indisponível no momento.");
    }
}
