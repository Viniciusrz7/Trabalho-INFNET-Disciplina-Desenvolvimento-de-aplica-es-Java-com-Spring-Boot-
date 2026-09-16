package infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.dto;

import infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.domain.Entrega;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record EntregaRequest(
        @NotNull(message = "A lanchonete é obrigatória")
        @NotBlank(message = "O nome é obrigatório") String nomeCliente,
        @NotBlank(message = "O endereço é obrigatório") String endereco,
        @NotNull(message = "O frete é obrigatório") BigDecimal frete,
        @NotNull(message = "A data é obrigatória")
        @FutureOrPresent(message = "A data não pode ser no passado") LocalDateTime data,
        @NotNull(message = "O horário é obrigatório") LocalTime hora,
        @NotNull(message = "O valor da entrega é obrigatório") BigDecimal valorEntrega,
        boolean ativa
) {
    public Entrega toEntity() {
        Entrega entrega = new Entrega(null, nomeCliente, endereco, frete, data, hora, valorEntrega);
        entrega.setAtiva(ativa);
        return entrega;
    }
}
