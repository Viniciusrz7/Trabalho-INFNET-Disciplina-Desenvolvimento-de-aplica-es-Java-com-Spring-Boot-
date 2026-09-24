package br.edu.infnet.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EntregaProcessor implements ItemProcessor<EntregaBatch,EntregaBatch> {

    private static final Logger log = LoggerFactory.getLogger(EntregaProcessor.class);

    @Override
    public EntregaBatch process(EntregaBatch entrega) throws Exception {

        EntregaBatch processador = new EntregaBatch(entrega.getId(),
                entrega.getNomeCliente().trim().toUpperCase(),
                entrega.getEndereco().trim().toLowerCase(),
                entrega.getFrete(),
                entrega.getData(),
                entrega.getHora(),
                entrega.getValorEntrega(),
                entrega.isAtiva());

        log.info("Processor: {} -> {}", entrega, processador);
        return processador;
    }

}
