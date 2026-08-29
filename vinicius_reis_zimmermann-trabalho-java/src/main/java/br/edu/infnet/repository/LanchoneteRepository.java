package br.edu.infnet.repository;

import br.edu.infnet.model.domain.Lanchonete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanchoneteRepository extends JpaRepository<Lanchonete, Long> {
    List<Lanchonete> findByNomeContainingIgnoreCase(String nome);
}
