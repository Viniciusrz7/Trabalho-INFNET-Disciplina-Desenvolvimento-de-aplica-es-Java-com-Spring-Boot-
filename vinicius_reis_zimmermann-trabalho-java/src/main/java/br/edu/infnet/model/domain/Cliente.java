package br.edu.infnet.model.domain;

import br.edu.infnet.model.domain.util.CPF;
import br.edu.infnet.model.domain.util.Identificavel;

public class Cliente implements Identificavel {
    private Long id;
    private String nome;
    private CPF cpf;
    private Lanchonete lanchonete;

    public Cliente(Long id, String nome, CPF cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente(){

    }

    @Override
    public String toString() {
        String numeroCpf = (cpf != null) ? cpf.valor() : "Não informado";
        return String.format("Cliente {nome='%s', cpf='%s'}", nome, numeroCpf);
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
    public CPF getCpf() {
        return cpf;
    }
    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }
    public Lanchonete getLanchonete() {
        return lanchonete;
    }
    public void setLanchonete(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }
}
