package br.edu.infnet.repository;

import br.edu.infnet.model.domain.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio,Long> {

}
