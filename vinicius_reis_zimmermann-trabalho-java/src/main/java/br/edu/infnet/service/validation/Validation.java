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
}
