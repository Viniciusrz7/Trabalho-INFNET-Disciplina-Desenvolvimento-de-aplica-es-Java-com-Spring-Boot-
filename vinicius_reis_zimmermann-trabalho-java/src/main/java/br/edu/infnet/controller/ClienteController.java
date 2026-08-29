package br.edu.infnet.controller;

import br.edu.infnet.model.domain.Cliente;
import br.edu.infnet.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Inclui um cliente", description = "Cadastra um novo cliente na aplicação")
    @PostMapping
    public ResponseEntity<Cliente> incluir(@Valid @RequestBody Cliente cliente) {
        clienteService.incluir(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(location).body(cliente);
    }

    @Operation(summary = "Busca clientes por nome", description = "Retorna os clientes que possuem o trecho informado no nome")
    @GetMapping("/busca")
    public ResponseEntity<List<Cliente>> getByName(@Parameter(description = "Trecho do nome do cliente") @RequestParam String nome) {
        List<Cliente> clientes = clienteService.buscarPorNome(nome);
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Busca um cliente por ID", description = "Retorna um cliente através do seu identificador")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@Parameter(description = "ID do cliente") @PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Altera um cliente", description = "Altera todos os dados de um cliente")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> alterar(@Parameter(description = "ID do cliente") @PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        cliente.setId(id);
        clienteService.alterar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Altera parcialmente um cliente", description = "Altera somente os dados informados do cliente")
    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> alterarParcialmente(@Parameter(description = "ID do cliente") @PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente atualizado = clienteService.alterarParcialmente(id, cliente);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Lista todos os clientes", description = "Retorna todos os clientes cadastrados na aplicação")
    @GetMapping
    public ResponseEntity<List<Cliente>> obterLista() {
        List<Cliente> clientes = clienteService.obterLista();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Exclui um cliente", description = "Exclui um cliente através do seu identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID do cliente") @PathVariable Long id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
