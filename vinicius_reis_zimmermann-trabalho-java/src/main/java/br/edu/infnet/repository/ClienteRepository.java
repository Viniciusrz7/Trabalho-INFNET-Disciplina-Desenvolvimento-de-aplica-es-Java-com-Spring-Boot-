package br.edu.infnet.repository;

import br.edu.infnet.model.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
