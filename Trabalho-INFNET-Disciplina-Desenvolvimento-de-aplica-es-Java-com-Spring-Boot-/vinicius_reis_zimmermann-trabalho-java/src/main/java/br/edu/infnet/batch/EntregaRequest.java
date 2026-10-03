package br.edu.infnet.batch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class EntregaRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String nomeCliente;

    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;

    @NotNull(message = "O frete é obrigatório")
    private BigDecimal frete;

    @NotNull(message = "O valor da entrega é obrigatório")
    private BigDecimal valorEntrega;

    private boolean ativa;

    public EntregaRequest(String nomeCliente, String endereco, BigDecimal frete, BigDecimal valorEntrega, boolean ativa) {
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.frete = frete;
        this.valorEntrega = valorEntrega;
        this.ativa = ativa;
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

