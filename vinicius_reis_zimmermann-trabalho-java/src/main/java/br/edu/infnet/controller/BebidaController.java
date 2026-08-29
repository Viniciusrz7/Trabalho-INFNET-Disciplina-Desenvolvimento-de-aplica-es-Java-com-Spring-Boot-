package br.edu.infnet.controller;

import br.edu.infnet.model.domain.Bebida;
import br.edu.infnet.service.BebidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bebidas")
public class BebidaController {

    private final BebidaService bebidaService;

    public BebidaController(BebidaService bebidaService) {
        this.bebidaService = bebidaService;
    }

    @Operation(summary = "Inclui uma bebida", description = "Cadastra uma nova bebida na aplicação")
    @PostMapping
    public ResponseEntity<Bebida> incluir(@Valid @RequestBody Bebida bebida) {
        bebidaService.incluir(bebida);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bebida.getId())
                .toUri();
        return ResponseEntity.created(location).body(bebida);
    }

    @Operation(summary = "Lista todas as bebidas", description = "Retorna todas as bebidas da aplicação")
    @GetMapping
    public ResponseEntity<List<Bebida>> obterLista() {
        List<Bebida> bebidas = bebidaService.obterLista();
        return ResponseEntity.ok(bebidas);
    }

    @Operation(summary = "Lista as bebidas disponíveis", description = "Retorna todas as bebidas disponíveis")
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Bebida>> obterDisponiveis() {
        List<Bebida> bebidas = bebidaService.obterDisponiveis();
        return ResponseEntity.ok(bebidas);
    }

    @Operation(summary = "Busca uma bebida por ID", description = "Retorna uma bebida através do seu identificador")
    @GetMapping("/{id}")
    public ResponseEntity<Bebida> getById(@Parameter(description = "ID da bebida") @PathVariable Long id) {
        Bebida bebida = bebidaService.getById(id);
        return ResponseEntity.ok(bebida);
    }

    @Operation(summary = "Busca bebidas por nome", description = "Retorna as bebidas que possuem o trecho informado no nome")
    @GetMapping("/busca")
    public ResponseEntity<List<Bebida>> getByName(@Parameter(description = "Trecho do nome da bebida") @RequestParam String nome) {
        List<Bebida> bebidas = bebidaService.buscarPorNome(nome);
        return ResponseEntity.ok(bebidas);
    }

    @Operation(summary = "Altera uma bebida", description = "Altera todos os dados de uma bebida")
    @PutMapping("/{id}")
    public ResponseEntity<Bebida> alterar(@Parameter(description = "ID da bebida") @PathVariable Long id, @Valid @RequestBody Bebida bebida) {
        bebida.setId(id);
        bebidaService.alterar(id, bebida);
        return ResponseEntity.ok(bebida);
    }

    @Operation(summary = "Altera parcialmente uma bebida", description = "Altera somente os dados informados da bebida")
    @PatchMapping("/{id}")
    public ResponseEntity<Bebida> alterarParcialmente(@Parameter(description = "ID da bebida") @PathVariable Long id, @RequestBody Bebida bebida) {
        Bebida atualizada = bebidaService.alterarParcialmente(id, bebida);
        return ResponseEntity.ok(atualizada);
    }

    @Operation(summary = "Exclui uma bebida", description = "Exclui uma bebida através do seu identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID da bebida") @PathVariable Long id) {
        bebidaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
