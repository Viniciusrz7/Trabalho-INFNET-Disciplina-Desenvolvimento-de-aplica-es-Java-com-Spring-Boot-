package br.edu.infnet.entrega.client;

import br.edu.infnet.entrega.client.exception.EntregaRemotoNaoEncontradoException;
import br.edu.infnet.entrega.client.exception.EntregaServiceIndisponivelException;
import feign.FeignException;
import feign.RetryableException;
import org.springframework.stereotype.Component;

@Component
public class EntregaGateway {

    private final EntregaClient entregaClient;

    public EntregaGateway(EntregaClient entregaClient) {
        this.entregaClient = entregaClient;
    }

    public EntregaResponse obterPorId(Long id) {
        try {
            return entregaClient.obterPorId(id);
        } catch (FeignException.NotFound e) {
            throw new EntregaRemotoNaoEncontradoException(id);
        } catch (RetryableException e) {
            throw new EntregaServiceIndisponivelException();
        }
    }

}
