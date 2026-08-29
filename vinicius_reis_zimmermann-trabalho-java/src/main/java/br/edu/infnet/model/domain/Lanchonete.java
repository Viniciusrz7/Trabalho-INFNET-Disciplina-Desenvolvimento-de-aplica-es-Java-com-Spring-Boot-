package br.edu.infnet.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import br.edu.infnet.model.domain.util.Identificavel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Entity
@Table(name = "Lanchonete")
public class Lanchonete implements Identificavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome deve ser informado")
    @Size(max = 150, message = "O nome deve possuir no máximo 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nome;

    @NotNull(message = "Informe se a lanchonete está ativa")
    @Column(nullable = false)
    private Boolean ativa;

    @NotBlank(message = "O CNPJ é obrigatório")
    @CNPJ(message = "CNPJ inválido")
    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    @JsonManagedReference
    @OneToMany(mappedBy = "lanchonete")
    private List<ItemCardapio> cardapio = new ArrayList<ItemCardapio>();

    @OneToMany(mappedBy = "lanchonete")
    private List<Pedido> historicoPedidos = new ArrayList<Pedido>();

    @OneToMany(mappedBy = "lanchonete")
    private List<Cliente> clientesCadastrados = new ArrayList<Cliente>();

    public Lanchonete(Long id, String nome, Boolean ativa, String cnpj) {
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
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
    public Boolean isAtiva() {
        return ativa;
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
