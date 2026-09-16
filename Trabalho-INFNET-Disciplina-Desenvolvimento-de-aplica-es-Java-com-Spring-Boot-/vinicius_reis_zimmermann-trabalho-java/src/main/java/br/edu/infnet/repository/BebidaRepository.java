package br.edu.infnet.repository;

import br.edu.infnet.model.domain.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BebidaRepository extends JpaRepository<Bebida, Long> {
    List<Bebida> findByDisponivelTrue();
    List<Bebida> findByNomeContainingIgnoreCase(String nome);
}
