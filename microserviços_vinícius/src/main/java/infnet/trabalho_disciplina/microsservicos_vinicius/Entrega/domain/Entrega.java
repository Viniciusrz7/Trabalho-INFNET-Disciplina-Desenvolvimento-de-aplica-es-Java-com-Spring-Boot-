package infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A lanchonete é obrigatória")
    @Column(name = "lanchonete_id", nullable = false)
    private Long lanchoneteId;


    @NotBlank(message = "O nome é obrigatorio")
    private String nomeCliente;

    @NotBlank(message = "Endereço Obrigatorio")
    private String endereco;

    @NotNull(message = "O frete é obrigatorio")
    private BigDecimal frete;

    @NotNull(message = "data obrigatoria")
    @FutureOrPresent(message = "a data nao pode ser no passado")
    private LocalDateTime data;

    @NotNull(message = "deve se ter horario")
    private LocalTime hora;

    @NotNull(message = "O valorEntrega é obrigatorio")
    private BigDecimal valorEntrega ;

    private boolean ativa;

    protected Entrega() {
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public Entrega(Long id, String nomeCliente, String endereco, BigDecimal frete, LocalDateTime data, LocalTime hora, BigDecimal valorEntrega) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.frete = frete;
        this.data = data;
        this.hora = hora;
        this.valorEntrega = valorEntrega;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLanchoneteId() {
        return lanchoneteId;
    }

    public void setLanchoneteId(Long lanchoneteId) {
        this.lanchoneteId = lanchoneteId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getFrete() {
        return frete;
    }

    public void setFrete(BigDecimal frete) {
        this.frete = frete;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public BigDecimal getValorEntrega() {
        return valorEntrega;
    }

    public void setValorEntrega(BigDecimal valorEntrega) {
        this.valorEntrega = valorEntrega;
    }

}
