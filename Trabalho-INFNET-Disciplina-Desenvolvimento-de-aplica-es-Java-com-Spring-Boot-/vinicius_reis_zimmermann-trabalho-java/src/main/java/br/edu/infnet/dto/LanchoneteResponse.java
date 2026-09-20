package br.edu.infnet.dto;


import java.util.List;
import java.util.Set;

public record LanchoneteResponse(Long id, String nome, Boolean ativa, Set<Long> entregaIds) {
}
