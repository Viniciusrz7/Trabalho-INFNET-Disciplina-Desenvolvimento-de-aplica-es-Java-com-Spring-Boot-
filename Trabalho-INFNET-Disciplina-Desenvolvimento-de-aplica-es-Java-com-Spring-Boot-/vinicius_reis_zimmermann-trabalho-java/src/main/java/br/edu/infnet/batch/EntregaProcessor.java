package br.edu.infnet.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EntregaProcessor implements ItemProcessor<EntregaBatch, EntregaRequest> {

    private static final Logger log = LoggerFactory.getLogger(EntregaProcessor.class);

    @Override
    public EntregaRequest process(EntregaBatch entrega) throws Exception {

        if (!entrega.isAtiva()) {
            log.info("Processor: entrega ignorada -> {}", entrega);
            return null;
        }

        EntregaRequest processador = new EntregaRequest(
                entrega.getNomeCliente().trim().toUpperCase(),
                entrega.getEndereco().trim().toLowerCase(),
                entrega.getFrete(),
                entrega.getValorEntrega(),
                entrega.isAtiva());

        log.info("Processor: {} -> {}", entrega, processador);
        return processador;
    }

}
