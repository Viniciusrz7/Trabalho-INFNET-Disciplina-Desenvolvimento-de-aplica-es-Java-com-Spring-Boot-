package br.edu.infnet.repository;

import br.edu.infnet.model.domain.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ItemCardapioRepository extends JpaRepository<ItemCardapio,Long> {
    List<ItemCardapio> findByDisponivelTrue();
    List<ItemCardapio>findByNomeContainingIgnoreCase(String nome);
}
