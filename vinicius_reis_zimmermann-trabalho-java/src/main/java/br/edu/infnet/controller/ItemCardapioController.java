package br.edu.infnet.controller;

import br.edu.infnet.model.domain.ItemCardapio;
import br.edu.infnet.service.ItemCardapioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/itemcardapios")
public class ItemCardapioController {

    private final ItemCardapioService itemCardapioService;

    public ItemCardapioController(ItemCardapioService itemCardapioService) {
        this.itemCardapioService = itemCardapioService;
    }

    @Operation(summary = "Inclui um ItemCardapio", description = "Cadastra um novo ItemCardapio na aplicação")
    @PostMapping
    public ResponseEntity<ItemCardapio> incluir(@Valid @RequestBody ItemCardapio itemCardapio) {
        itemCardapioService.incluir(itemCardapio);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itemCardapio.getId())
                .toUri();
        return ResponseEntity.created(location).body(itemCardapio);
    }

    @Operation(summary = "Lista todos os ItemCardapios", description = "Retorna todos os ItemCardapios da aplicação")
    @GetMapping
    public ResponseEntity<List<ItemCardapio>> obterLista() {
        List<ItemCardapio> itemCardapios = itemCardapioService.obterLista();
        return ResponseEntity.ok(itemCardapios);
    }

    @Operation(summary = "Lista os ItemCardapios disponíveis", description = "Retorna todos os ItemCardapios disponíveis")
    @GetMapping("/disponiveis")
    public ResponseEntity<List<ItemCardapio>> obterDisponiveis() {
        List<ItemCardapio> itemCardapios = itemCardapioService.obterDisponiveis();
        return ResponseEntity.ok(itemCardapios);
    }

    @Operation(summary = "Busca um ItemCardapio por ID", description = "Retorna um ItemCardapio através do seu identificador")
    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapio> getById(@Parameter(description = "ID do ItemCardapio") @PathVariable Long id) {
        ItemCardapio itemCardapio = itemCardapioService.getById(id);
        return ResponseEntity.ok(itemCardapio);
    }

    @Operation(summary = "Busca ItemCardapios por nome", description = "Retorna os ItemCardapios que possuem o trecho informado no nome")
    @GetMapping("/busca")
    public ResponseEntity<List<ItemCardapio>> getByName(@Parameter(description = "Trecho do nome do ItemCardapio") @RequestParam String nome) {
        List<ItemCardapio> itemCardapios = itemCardapioService.buscarPorNome(nome);
        return ResponseEntity.ok(itemCardapios);
    }

    @Operation(summary = "Altera um ItemCardapio", description = "Altera todos os dados de um ItemCardapio")
    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapio> alterar(@Parameter(description = "ID do ItemCardapio") @PathVariable Long id, @Valid @RequestBody ItemCardapio itemCardapio) {
        itemCardapio.setId(id);
        itemCardapioService.alterar(id, itemCardapio);
        return ResponseEntity.ok(itemCardapio);
    }

    @Operation(summary = "Altera parcialmente um ItemCardapio", description = "Altera somente os dados informados do ItemCardapio")
    @PatchMapping("/{id}")
    public ResponseEntity<ItemCardapio> alterarParcialmente(@Parameter(description = "ID do ItemCardapio") @PathVariable Long id, @RequestBody ItemCardapio itemCardapio) {
        ItemCardapio atualizado = itemCardapioService.alterarParcialmente(id, itemCardapio);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Exclui um ItemCardapio", description = "Exclui um ItemCardapio através do seu identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID do ItemCardapio") @PathVariable Long id) {
        itemCardapioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
