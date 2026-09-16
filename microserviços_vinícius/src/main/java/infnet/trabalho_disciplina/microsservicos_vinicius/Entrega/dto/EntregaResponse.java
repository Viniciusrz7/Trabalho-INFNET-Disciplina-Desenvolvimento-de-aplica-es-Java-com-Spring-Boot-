package infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.dto;

import infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.domain.Entrega;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record EntregaResponse(
        Long id,
        String nomeCliente,
        String endereco,
        BigDecimal frete,
        LocalDateTime data,
        LocalTime hora,
        BigDecimal valorEntrega,
        boolean ativa
) {
    public static EntregaResponse from(Entrega entrega) {
        return new EntregaResponse(entrega.getId(),
                entrega.getNomeCliente(), entrega.getEndereco(), entrega.getFrete(),
                entrega.getData(), entrega.getHora(), entrega.getValorEntrega(), entrega.isAtiva());
    }
}
