package br.edu.infnet.controller;

import br.edu.infnet.model.domain.Lanche;
import br.edu.infnet.service.LancheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lanches")
public class LancheController {

    private final LancheService lancheService;

    public LancheController(LancheService lancheService) {
        this.lancheService = lancheService;
    }

    @Operation(summary = "Inclui um lanche", description = "Cadastra um novo lanche na aplicação")
    @PostMapping
    public ResponseEntity<Lanche> incluir(@Valid @RequestBody Lanche lanche) {
        lancheService.incluir(lanche);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lanche.getId())
                .toUri();
        return ResponseEntity.created(location).body(lanche);
    }

    @Operation(summary = "Lista todos os lanches", description = "Retorna todos os lanches da aplicação")
    @GetMapping
    public ResponseEntity<List<Lanche>> obterLista() {
        List<Lanche> lanches = lancheService.obterLista();
        return ResponseEntity.ok(lanches);
    }

    @Operation(summary = "Lista os lanches disponíveis", description = "Retorna todos os lanches disponíveis")
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Lanche>> obterDisponiveis() {
        List<Lanche> lanches = lancheService.obterDisponiveis();
        return ResponseEntity.ok(lanches);
    }

    @Operation(summary = "Busca um lanche por ID", description = "Retorna um lanche através do seu identificador")
    @GetMapping("/{id}")
    public ResponseEntity<Lanche> getById(@Parameter(description = "ID do lanche") @PathVariable Long id) {
        Lanche lanche = lancheService.getById(id);
        return ResponseEntity.ok(lanche);
    }

    @Operation(summary = "Busca lanches por nome", description = "Retorna os lanches que possuem o trecho informado no nome")
    @GetMapping("/busca")
    public ResponseEntity<List<Lanche>> getByName(@Parameter(description = "Trecho do nome do lanche") @RequestParam String nome) {
        List<Lanche> lanches = lancheService.buscarPorNome(nome);
        return ResponseEntity.ok(lanches);
    }

    @Operation(summary = "Altera um lanche", description = "Altera todos os dados de um lanche")
    @PutMapping("/{id}")
    public ResponseEntity<Lanche> alterar(@Parameter(description = "ID do lanche") @PathVariable Long id, @Valid @RequestBody Lanche lanche) {
        lanche.setId(id);
        lancheService.alterar(id, lanche);
        return ResponseEntity.ok(lanche);
    }

    @Operation(summary = "Altera parcialmente um lanche", description = "Altera somente os dados informados do lanche")
    @PatchMapping("/{id}")
    public ResponseEntity<Lanche> alterarParcialmente(@Parameter(description = "ID do lanche") @PathVariable Long id, @RequestBody Lanche lanche) {
        Lanche atualizado = lancheService.alterarParcialmente(id, lanche);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Exclui um lanche", description = "Exclui um lanche através do seu identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID do lanche") @PathVariable Long id) {
        lancheService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
