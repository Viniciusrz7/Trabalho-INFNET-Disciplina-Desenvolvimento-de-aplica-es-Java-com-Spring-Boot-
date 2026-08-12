package br.edu.infnet.controller;

import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.ItemCardapio;
import br.edu.infnet.service.ItemCardapioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemcardapios")
public class ItemCardapioController {

    private final ItemCardapioService itemCardapioService;

    public ItemCardapioController(ItemCardapioService itemCardapioService){
        this.itemCardapioService = itemCardapioService;
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapio>> obterLista(){
        List<ItemCardapio> itemCardapios = itemCardapioService.obterLista();
        return ResponseEntity.ok(itemCardapios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapio> getById(@PathVariable Long id) {
        try {
            ItemCardapio itemCardapio = itemCardapioService.getById(id);

            return ResponseEntity.ok(itemCardapio);
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();       }


    }

}
