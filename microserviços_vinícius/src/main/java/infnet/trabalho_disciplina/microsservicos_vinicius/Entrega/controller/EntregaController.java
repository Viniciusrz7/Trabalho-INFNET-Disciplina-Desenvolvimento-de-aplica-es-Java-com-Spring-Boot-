package infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.controller;

import infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.service.EntregaService;
import infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.domain.Entrega;
import infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.dto.EntregaRequest;
import infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.dto.EntregaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping
    public ResponseEntity<EntregaResponse> incluir(@Valid @RequestBody EntregaRequest entrega){
        Entrega incluido = entregaService.incluir(entrega.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body(EntregaResponse.from(incluido));
    }

    @GetMapping
    public List<EntregaResponse> obterLista(){
        return entregaService.obterLista().stream().map(EntregaResponse::from).toList();
    }

   /*
    @GetMapping("/lanchonete/{lanchoneteId}")
    public List<EntregaResponse> obterPorLanchonete(@PathVariable Long lanchoneteId) {
        return entregaService.obterPorLanchonete(lanchoneteId)
                .stream().map(EntregaResponse::from).toList();
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(entregaService.obterPorId(id));
    }

    @PutMapping("/{id}")
    public EntregaResponse alterar(@PathVariable Long id, @Valid @RequestBody EntregaRequest entrega){
        return EntregaResponse.from(entregaService.alterar(id, entrega.toEntity()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        entregaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativas")
    public List<EntregaResponse> obterAtivas(){
        return entregaService.obterAtivos().stream().map(EntregaResponse::from).toList();
    }

    @GetMapping("/buscarNome")
    public List<EntregaResponse> obterPorNome(@RequestParam String nome){
        return entregaService.obterPorNome(nome).stream().map(EntregaResponse::from).toList();
    }

    @GetMapping("/buscarEndereco")
    public Optional<EntregaResponse> obterPorEndereco(@RequestParam String endereco){
        return entregaService.obterPorEndereco(endereco).map(EntregaResponse::from);
    }
}
