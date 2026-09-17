package infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.dto;

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
){}
