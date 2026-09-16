package br.edu.infnet.repository;


import br.edu.infnet.model.domain.Lanche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancheRepository extends JpaRepository<Lanche,Long> {
    List<Lanche> findByDisponivelTrue();
    List<Lanche> findByNomeContainingIgnoreCase(String nome);
}
