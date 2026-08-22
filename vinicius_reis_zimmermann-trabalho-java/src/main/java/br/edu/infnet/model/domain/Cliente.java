package br.edu.infnet.model.domain;

import br.edu.infnet.model.domain.util.Identificavel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente implements Identificavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @JsonIgnore
    @Transient
    private Lanchonete lanchonete;

    public Cliente(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente() {
    }

    @Override
    public String toString() {
        return String.format("Cliente {nome='%s', cpf='%s'}",
                nome,
                cpf != null ? cpf : "Não informado");
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
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Lanchonete getLanchonete() {
        return lanchonete;
    }
    public void setLanchonete(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }
}