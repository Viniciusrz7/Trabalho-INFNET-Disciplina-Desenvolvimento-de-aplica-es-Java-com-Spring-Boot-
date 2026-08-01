package br.edu.infnet.model.domain.util;

public final class CNPJ {

    private final String valor;

    public CNPJ(String valor) {
        this.valor = limpar(valor);

        if (!validar(this.valor)) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }
    }

    public String valor() {
        return valor;
    }

    private String limpar(String cnpj) {
        return cnpj == null ? "" : cnpj.replaceAll("\\D", "");
    }

    private boolean validar(String cnpj) {
        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int[] pesosPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesosSegundoDigito = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        return calcularDigito(cnpj, pesosPrimeiroDigito) == cnpj.charAt(12) - '0'
                && calcularDigito(cnpj, pesosSegundoDigito) == cnpj.charAt(13) - '0';
    }

    private int calcularDigito(String cnpj, int[] pesos) {
        int soma = 0;

        for (int i = 0; i < pesos.length; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos[i];
        }

        int resto = soma % 11;

        return resto < 2 ? 0 : 11 - resto;
    }

    @Override
    public String toString() {
        return valor;
    }

}