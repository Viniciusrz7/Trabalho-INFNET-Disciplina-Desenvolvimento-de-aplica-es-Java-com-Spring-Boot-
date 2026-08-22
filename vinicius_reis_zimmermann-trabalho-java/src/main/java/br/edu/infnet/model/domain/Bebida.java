package br.edu.infnet.model.domain;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Bebida extends ItemCardapio{
    private Integer volumeMl;
    private Boolean eAlcoolica;

    public Bebida(Long id, String nome, BigDecimal preco, Boolean disponivel, Integer volumeMl, Boolean eAlcoolica) {
        super(id, nome, preco, disponivel);
        this.volumeMl = volumeMl;
        this.eAlcoolica = eAlcoolica;
    }

    public Bebida(){

    }

    @Override
    public String toString() {
        return String.format("%s, volumeMl='%d', alcoolica='%s'}",
                super.toString().replace("}", ""),
                volumeMl,
                eAlcoolica ? "sim" : "não"
        );
    }

    @Override
    public String descreverPreparo() {
        String tipoAlcool = eAlcoolica ? "Com Álcool" : "Sem Álcool";
        return String.format("Separando Bebida: %s (%dml) - %s", getNome(), getVolumeMl(), tipoAlcool);
    }

    public Integer getVolumeMl() {
        return volumeMl;
    }
    public void setVolumeMl(Integer volumeMl) {
        this.volumeMl = volumeMl;
    }
    public Boolean iseAlcoolica() {
        return eAlcoolica;
    }
    public void seteAlcoolica(Boolean eAlcoolica) {
        this.eAlcoolica = eAlcoolica;
    }

}
