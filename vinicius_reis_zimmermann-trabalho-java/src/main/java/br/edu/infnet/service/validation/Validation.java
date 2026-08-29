package br.edu.infnet.service.validation;

public final class Validation {

    private Validation() {
    }

    public static void validarTermo(String termo) {
        if (termo == null || termo.isBlank()) {
            throw new IllegalArgumentException("O termo de busca não pode ser vazio ou nulo.");
        }
        if (termo.trim().length() < 3) {
            throw new IllegalArgumentException("O termo de busca deve ter pelo menos 3 caracteres.");
        }
    }

    public static String validarCep(String cep) {
        if (cep == null || cep.isBlank()) {
            throw new IllegalArgumentException("O CEP não pode ser vazio ou nulo.");
        }
        String cepNumerico = cep.replaceAll("\\D", "");
        if (cepNumerico.length() != 8) {
            throw new IllegalArgumentException("O CEP deve possuir 8 dígitos numéricos.");
        }
        return cepNumerico;
    }
}
