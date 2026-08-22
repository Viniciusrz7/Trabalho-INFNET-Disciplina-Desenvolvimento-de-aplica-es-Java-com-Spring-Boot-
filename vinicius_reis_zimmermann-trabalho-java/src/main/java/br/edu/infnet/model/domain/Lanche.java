package br.edu.infnet.model.domain;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
@Entity
public class Lanche extends ItemCardapio{
    private String ingredientes;
    private Boolean eArtesanal;

    public Lanche(Long id, String nome, BigDecimal preco, Boolean disponivel, String ingredientes, Boolean eArtesanal) {
        super(id, nome, preco, disponivel);
        this.ingredientes = ingredientes;
        this.eArtesanal = eArtesanal;
    }
    public Lanche(){

    }

    @Override
    public String descreverPreparo() {
        String tipo = eArtesanal ? "artesanal (preparo especial na chapa)" : "tradicional";
        return String.format("Preparando %s %s com os ingredientes: %s",
                getNome(), tipo, getIngredientes());
    }

    @Override
    public String toString() {
        return String.format("%s, ingredientes='%s', artesanal='%s'}",
                super.toString().replace("}", ""),
                ingredientes,
                eArtesanal ? "sim" : "não"
        );
    }

    public String getIngredientes() {
        return ingredientes;
    }
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
    public Boolean iseArtesanal() {
        return eArtesanal;
    }
    public void setArtesanal(Boolean eArtesanal) {
        this.eArtesanal = eArtesanal;
    }

}
