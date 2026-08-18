package br.edu.infnet.model.domain;

import br.edu.infnet.model.domain.util.Identificavel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itemcardapios")
public abstract class ItemCardapio implements Identificavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String nome;
    @Column(nullable = false,length = 200)
    private BigDecimal preco;
    private Boolean disponivel;

    @JsonBackReference
    @Transient // não considera ele agora não para não dar erro, depois vai ter relacionamento
    private Lanchonete lanchonete;

    public ItemCardapio(Long id,String nome, BigDecimal preco, Boolean disponivel) {
        this.id=id;
        this.nome = nome;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    protected ItemCardapio(){

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
