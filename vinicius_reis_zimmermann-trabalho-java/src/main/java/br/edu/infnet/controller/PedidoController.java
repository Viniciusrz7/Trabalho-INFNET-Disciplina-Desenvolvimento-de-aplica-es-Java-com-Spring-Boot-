package br.edu.infnet.controller;

import br.edu.infnet.model.domain.Pedido;
import br.edu.infnet.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Inclui um pedido", description = "Cadastra um novo pedido na aplicação")
    @PostMapping
    public ResponseEntity<Pedido> incluir(@Valid @RequestBody Pedido pedido) {
        pedidoService.incluir(pedido);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();
        return ResponseEntity.created(location).body(pedido);
    }

    @Operation(summary = "Lista todos os pedidos", description = "Retorna todos os pedidos da aplicação")
    @GetMapping
    public ResponseEntity<List<Pedido>> obterLista() {
        List<Pedido> pedidos = pedidoService.obterLista();
        return ResponseEntity.ok(pedidos);
    }

    @Operation(summary = "Busca um pedido por ID", description = "Retorna um pedido através do seu identificador")
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@Parameter(description = "ID do pedido") @PathVariable Long id) {
        Pedido pedido = pedidoService.getById(id);
        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Altera um pedido", description = "Altera todos os dados de um pedido")
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> alterar(@Parameter(description = "ID do pedido") @PathVariable Long id, @Valid @RequestBody Pedido pedido) {
        pedido.setId(id);
        pedidoService.alterar(id, pedido);
        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Altera parcialmente um pedido", description = "Altera somente os dados informados do pedido")
    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> alterarParcialmente(@Parameter(description = "ID do pedido") @PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido atualizado = pedidoService.alterarParcialmente(id, pedido);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Exclui um pedido", description = "Exclui um pedido através do seu identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID do pedido") @PathVariable Long id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
