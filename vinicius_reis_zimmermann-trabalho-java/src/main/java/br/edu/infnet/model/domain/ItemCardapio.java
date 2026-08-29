package br.edu.infnet.model.domain;

import br.edu.infnet.model.domain.util.Identificavel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "itemcardapios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemCardapio implements Identificavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 150, message = "O nome deve possuir no máximo 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nome;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @NotNull(message = "A disponibilidade é obrigatória")
    @Column(nullable = false)
    private Boolean disponivel;

    @JsonBackReference
    @NotNull(message = "A lanchonete é obrigatória")
    @ManyToOne
    @JoinColumn(name = "lanchonete_id", nullable = false)
    private Lanchonete lanchonete;


    public Boolean getDisponivel() {
        return disponivel;
    }

    public ItemCardapio(Long id, String nome, BigDecimal preco, Boolean disponivel) {
        this(nome);
        this.id=id;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    public ItemCardapio(String nome) {
        this.nome = nome;
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
