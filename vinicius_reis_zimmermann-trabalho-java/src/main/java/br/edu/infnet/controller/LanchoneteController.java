package br.edu.infnet.controller;

import br.edu.infnet.model.domain.Lanchonete;
import br.edu.infnet.service.LanchoneteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lanchonetes")
public class LanchoneteController {

    private final LanchoneteService lanchoneteService;

    public LanchoneteController(LanchoneteService lanchoneteService) {
        this.lanchoneteService = lanchoneteService;
    }

    @Operation(summary = "Inclui uma lanchonete", description = "Cadastra uma nova lanchonete na aplicação")
    @PostMapping
    public ResponseEntity<Lanchonete> incluir(@Valid @RequestBody Lanchonete lanchonete) {
        lanchoneteService.incluir(lanchonete);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lanchonete.getId())
                .toUri();
        return ResponseEntity.created(location).body(lanchonete);
    }

    @Operation(summary = "Busca lanchonetes por nome", description = "Retorna as lanchonetes que possuem o trecho informado no nome")
    @GetMapping(params = "nome")
    public ResponseEntity<List<Lanchonete>> getByName(@Parameter(description = "Trecho do nome da lanchonete") @RequestParam String nome) {
        List<Lanchonete> lanchonetes = lanchoneteService.buscarPorNome(nome);
        return ResponseEntity.ok(lanchonetes);
    }

    @Operation(summary = "Busca uma lanchonete por ID", description = "Retorna uma lanchonete através do seu identificador")
    @GetMapping("/{id}")
    public ResponseEntity<Lanchonete> getById(@Parameter(description = "ID da lanchonete") @PathVariable Long id) {
        Lanchonete lanchonete = lanchoneteService.getById(id);
        return ResponseEntity.ok(lanchonete);
    }

    @Operation(summary = "Altera uma lanchonete", description = "Altera todos os dados de uma lanchonete")
    @PutMapping("/{id}")
    public ResponseEntity<Lanchonete> alterar(@Parameter(description = "ID da lanchonete") @PathVariable Long id, @Valid @RequestBody Lanchonete lanchonete) {
        lanchonete.setId(id);
        lanchoneteService.alterar(id, lanchonete);
        return ResponseEntity.ok(lanchonete);
    }

    @Operation(summary = "Altera parcialmente uma lanchonete", description = "Altera somente os dados informados da lanchonete")
    @PatchMapping("/{id}")
    public ResponseEntity<Lanchonete> alterarParcialmente(@Parameter(description = "ID da lanchonete") @PathVariable Long id, @RequestBody Lanchonete lanchonete) {
        Lanchonete atualizada = lanchoneteService.alterarParcialmente(id, lanchonete);
        return ResponseEntity.ok(atualizada);
    }

    @Operation(summary = "Lista todas as lanchonetes", description = "Retorna todas as lanchonetes cadastradas na aplicação")
    @GetMapping
    public ResponseEntity<List<Lanchonete>> obterLista() {
        List<Lanchonete> lanchonetes = lanchoneteService.obterLista();
        return ResponseEntity.ok(lanchonetes);
    }

    @Operation(summary = "Exclui uma lanchonete", description = "Exclui uma lanchonete através do seu identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID da lanchonete") @PathVariable Long id) {
        lanchoneteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
