package br.edu.infnet.repository;


import br.edu.infnet.model.domain.Lanche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancheRepository extends JpaRepository<Lanche,Long> {
}
