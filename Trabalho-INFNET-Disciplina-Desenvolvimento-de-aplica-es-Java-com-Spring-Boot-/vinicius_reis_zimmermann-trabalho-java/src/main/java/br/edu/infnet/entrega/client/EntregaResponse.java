package br.edu.infnet.entrega.client;

import java.math.BigDecimal;

public record EntregaResponse(Long id, String nomeCliente, String endereco, BigDecimal valorEntrega) {

}
