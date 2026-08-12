package br.edu.infnet.model.domain.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public final class CPF {

    private final String valor;

    @JsonCreator
    public CPF(String valor) {
        this.valor = limpar(valor);

        if (!validar(this.valor)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    @JsonValue
    public String valor() {
        return valor;
    }

    private String limpar(String cpf) {
        return cpf == null ? "" : cpf.replaceAll("\\D", "");
    }

    private boolean validar(String cpf) {

        if (cpf.length() != 11)
            return false;

        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        return calcularDigito(cpf, 9) == cpf.charAt(9) - '0'
                && calcularDigito(cpf, 10) == cpf.charAt(10) - '0';
    }

    private int calcularDigito(String cpf, int tamanho) {

        int soma = 0;
        int peso = tamanho + 1;

        for (int i = 0; i < tamanho; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }

        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
}