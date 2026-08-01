package br.edu.infnet.model.domain;

public abstract class ItemCardapio {

    private String nome;
    private double preco;
    private boolean disponivel;
    private Lanchonete lanchonete;

    public ItemCardapio(String nome, double preco, boolean disponivel) {
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    public Lanchonete getLanchonete() {
        return lanchonete;
    }

    public void setLanchonete(Lanchonete lanchonete) {
        this.lanchonete = lanchonete;
    }
}
