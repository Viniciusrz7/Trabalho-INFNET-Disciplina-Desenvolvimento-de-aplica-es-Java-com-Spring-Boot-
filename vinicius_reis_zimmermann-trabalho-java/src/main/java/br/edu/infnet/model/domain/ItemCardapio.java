package br.edu.infnet.model.domain;

import br.edu.infnet.model.domain.util.Identificavel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public abstract class ItemCardapio implements Identificavel {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private Boolean disponivel;
    @JsonIgnore
    private Lanchonete lanchonete;

    public ItemCardapio(Long id,String nome, BigDecimal preco, Boolean disponivel) {
        this.id=id;
        this.nome = nome;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    public ItemCardapio(){

    }

    @Override
    public String toString() {
        return String.format("ItemCardapio {nome='%s',preco='%s',disponivel='%s'}",
                nome,
                preco,
                disponivel? "sim":"não"
        );
    }

    public abstract String descreverPreparo();

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    public Boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
    public Lanchonete getLanchonete() {
        return lanchonete;
    }
    public void setLanchonete(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
