package br.edu.infnet.model.domain;

import br.edu.infnet.model.domain.util.CNPJ;
import br.edu.infnet.model.domain.util.Identificavel;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lanchonete implements Identificavel {
    private Long id;
    private String nome;
    private Boolean ativa;
    private CNPJ cnpj;
    @JsonManagedReference
    private List<ItemCardapio> cardapio = new ArrayList<ItemCardapio>();
    private List<Pedido> historicoPedidos = new ArrayList<Pedido>();
    private List<Cliente> clientesCadastrados = new ArrayList<Cliente>();

    public Lanchonete(Long id, String nome, Boolean ativa, CNPJ cnpj) {
        this.id = id;
        this.nome = nome;
        this.ativa=ativa;
        this.cnpj = cnpj;
    }

    public Lanchonete(){

    }

    @Override
    public String toString() {
        return String.format("Lanchonete {nome='%s', cnpj='%s', ativa='%s', clientesCadastrados=%s, cardapio=%s, historicoPedidos=%s}",
                nome,
                cnpj,
                ativa ? "sim" : "não",
                clientesCadastrados,
                cardapio,
                historicoPedidos);
    }

    public void adicionarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("O Pedido não pode ser nulo");
        }
        historicoPedidos.add(pedido);
        pedido.setLanchonete(this);
    }

    public void adicionarItemcardapio(ItemCardapio itemCardapio){
            if(itemCardapio == null){
                throw new IllegalArgumentException("O Item cardapio não pode ser nulo");
            }
            cardapio.add(itemCardapio);
            itemCardapio.setLanchonete(this);
    }

    public void cadastrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O Cliente não pode ser nulo");
        }
        clientesCadastrados.add(cliente);
        cliente.setLanchonete(this);
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
    public CNPJ getCnpj() {
        return cnpj;
    }
    public void setCnpj(CNPJ cnpj) {
        this.cnpj = cnpj;
    }
    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
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
    public List<Cliente> getClientesCadastrados() {
        return Collections.unmodifiableList(clientesCadastrados);
    }
    public void setClientesCadastrados(List<Cliente> clientesCadastrados) {
        this.clientesCadastrados = clientesCadastrados;
    }
}
