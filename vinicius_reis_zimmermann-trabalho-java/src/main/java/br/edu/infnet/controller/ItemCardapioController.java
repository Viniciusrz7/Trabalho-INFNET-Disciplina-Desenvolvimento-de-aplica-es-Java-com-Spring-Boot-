package br.edu.infnet.controller;

import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.ItemCardapio;
import br.edu.infnet.service.ItemCardapioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/itemcardapios")
public class ItemCardapioController {

    private final ItemCardapioService itemCardapioService;

    public ItemCardapioController(ItemCardapioService itemCardapioService){
        this.itemCardapioService = itemCardapioService;
    }

    @PostMapping
    public ResponseEntity<ItemCardapio> incluir(@RequestBody ItemCardapio itemCardapio) {
        itemCardapioService.incluir(itemCardapio);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itemCardapio.getId())
                .toUri();

        // return ResponseEntity.status(HttpStatus.CREATED).body(itemCardapio);

        return ResponseEntity.created(location).body(itemCardapio);
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapio>> obterLista(){
        List<ItemCardapio> itemCardapios = itemCardapioService.obterLista();
        return ResponseEntity.ok(itemCardapios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapio> getById(@PathVariable Long id) {
            ItemCardapio itemCardapio = itemCardapioService.getById(id);
            return ResponseEntity.ok(itemCardapio);

    }

    @GetMapping(params = "nome")
    public ResponseEntity<List<ItemCardapio>> getByName(@RequestParam String nome) {

        List<ItemCardapio> itemCardapios = itemCardapioService.buscarPorNome(nome);

        return ResponseEntity.ok(itemCardapios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapio> alterar(@PathVariable Long id, @RequestBody ItemCardapio itemCardapio){

        itemCardapio.setId(id);

        itemCardapioService.alterar(itemCardapio);

        return ResponseEntity.ok(itemCardapio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        itemCardapioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
