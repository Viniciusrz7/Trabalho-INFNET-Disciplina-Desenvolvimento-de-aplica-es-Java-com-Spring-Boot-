package br.edu.infnet.controller;

import br.edu.infnet.model.domain.Cliente;
import br.edu.infnet.model.domain.ItemCardapio;
import br.edu.infnet.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService=clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> incluir(@Valid @RequestBody Cliente cliente) {

        clienteService.incluir(cliente);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(location).body(cliente);
    }

    @GetMapping(params = "nome")
    public ResponseEntity<List<Cliente>> getByName(@RequestParam String nome) {

        List<Cliente> clientes = clienteService.buscarPorNomeDeclarativo(nome);

        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> alterar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){

        cliente.setId(id);

        clienteService.alterar(cliente);

        return ResponseEntity.ok(cliente);
    }
    @GetMapping
    public ResponseEntity<List<Cliente>> obterLista(){
        List<Cliente> clientes = clienteService.obterLista();
        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
