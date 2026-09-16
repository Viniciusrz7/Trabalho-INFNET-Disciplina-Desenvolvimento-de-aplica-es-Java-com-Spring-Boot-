package infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.repository;

import infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.domain.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

   // List<Entrega> findByLanchoneteId(Long lanchoneteId);

    List<Entrega> findByAtivaTrue();

    List<Entrega> findByNomeClienteContainingIgnoreCase(String nomeCliente);

    Optional<Entrega> findByEndereco(String endereco);
}
