package br.edu.infnet.batch;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EntregaBatch {

    private Long id;
    private String nomeCliente;
    private String endereco;
    private BigDecimal frete;
    private LocalDateTime data;
    private LocalTime hora;
    private BigDecimal valorEntrega;
    private boolean ativa;


    public EntregaBatch(Long id, String nomeCliente, String endereco, BigDecimal frete, LocalDateTime data, LocalTime hora, BigDecimal valorEntrega, boolean ativa) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.frete = frete;
        this.data = data;
        this.hora = hora;
        this.valorEntrega = valorEntrega;
        this.ativa = ativa;
    }

    public String toString() {
        return "EntregaBatch{" +
                "id=" + id +
                ", nome='" + nomeCliente + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

}
