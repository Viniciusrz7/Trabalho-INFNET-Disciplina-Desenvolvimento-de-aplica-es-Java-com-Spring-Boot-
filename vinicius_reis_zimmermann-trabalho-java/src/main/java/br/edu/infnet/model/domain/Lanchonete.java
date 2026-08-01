package br.edu.infnet.model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lanchonete {
    private Long id;
    private String nome;
    private boolean ativa;
    private String cnpj;
    private List<ItemCardapio> cardapio = new ArrayList<ItemCardapio>();
    private List<Pedido> historicoPedidos = new ArrayList<Pedido>();

    public Lanchonete(Long id, String nome,boolean ativa, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.ativa=ativa;
        this.cnpj = cnpj;
    }

    public Lanchonete(){

    }

    @Override
    public String toString() {
        return String.format("Lanchonete {nome='%s', cnpj='%s', ativa='%s', cardapio=%s, historicoPedidos=%s}",
                nome,
                cnpj,
                ativa ? "sim" : "não",
                cardapio,
                historicoPedidos);
    }

    public void adicionarItemcardapio(ItemCardapio itemCardapio){
            if(itemCardapio == null){
                throw new IllegalArgumentException("O Item cardapio não pode ser nulo");
            }
            cardapio.add(itemCardapio);
            itemCardapio.setLanchonete(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<ItemCardapio> getCardapio() {
        return Collections.unmodifiableList(cardapio);
    }

    public void setCardapio(List<ItemCardapio> cardapio) {
        this.cardapio = cardapio;
    }

    public List<Pedido> getHistoricoPedidos() {
        return Collections.unmodifiableList(historicoPedidos);
    }

    public void setHistoricoPedidos(List<Pedido> historicoPedidos) {
        this.historicoPedidos = historicoPedidos;
    }
}
