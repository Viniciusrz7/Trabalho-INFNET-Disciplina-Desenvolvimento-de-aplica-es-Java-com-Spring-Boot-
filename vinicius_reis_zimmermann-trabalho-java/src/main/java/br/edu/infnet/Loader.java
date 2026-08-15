package br.edu.infnet;

import br.edu.infnet.exception.IdentificadorDuplicadoException;
import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.*;
import br.edu.infnet.service.ClienteService;
import br.edu.infnet.service.ItemCardapioService;
import br.edu.infnet.service.LanchoneteService;
import br.edu.infnet.service.PedidoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Loader implements CommandLineRunner {
    private final ItemCardapioService itemCardapioService;
    private final ClienteService clienteService;
    private final LanchoneteService lanchoneteService;
    private final PedidoService pedidoService;

    public Loader(ItemCardapioService itemCardapioService,
                  ClienteService clienteService,
                  LanchoneteService lanchoneteService,
                  PedidoService pedidoService) {
        this.itemCardapioService = itemCardapioService;
        this.clienteService = clienteService;
        this.lanchoneteService = lanchoneteService;
        this.pedidoService = pedidoService;
    }
    @Override
    public void run(String... args) throws Exception {


        Lanchonete lanchonete = new Lanchonete(1L, "Super Lanches", true, null    );

        Lanche lanche = new Lanche(1L, "X-Tudo Artesanal", new BigDecimal("35.50"), true, "Pão brioche, blend 200g, queijo, bacon, ovo", true);
        Bebida bebida = new Bebida(2L, "Coca-Cola", new BigDecimal("7.00"), true, 350, false);

        lanchonete.adicionarItemcardapio(lanche);
        lanchonete.adicionarItemcardapio(bebida);

        Cliente cliente = new Cliente(1L, "Carlos Silva", null);

        lanchonete.cadastrarCliente(cliente);

        Pedido pedido = new Pedido(1L, 1001, LocalDateTime.now(), true, cliente);

        pedido.adicionarItem(lanche);
        pedido.adicionarItem(bebida);

        lanchonete.adicionarPedido(pedido);

        List<ItemCardapio> dados = List.of(lanche);
        dados.forEach(System.out::println);
        System.out.println(" Dados da Lanchonete ");
        System.out.println(lanchonete);

        itemCardapioService.incluir(lanche);
        itemCardapioService.incluir(bebida);
        clienteService.incluir(cliente);
        lanchoneteService.incluir(lanchonete);
        pedidoService.incluir(pedido);

        for (ItemCardapio item : lanchonete.getCardapio()) {
            System.out.println("- " + item.descreverPreparo());
        }

        for (Pedido pedido1 : lanchonete.getHistoricoPedidos()) {
            System.out.println(pedido1);
        }
        try {
            itemCardapioService.incluir(lanche);
        } catch (IdentificadorDuplicadoException e) {
            System.out.println("[ERROR]" + e.getMessage() + "Nome:" + lanche.getNome());
        } catch (RecursoNaoEncontradoException | IllegalArgumentException e) {
            System.out.println("[ERROR]" + e.getMessage());
        }
         itemCardapioService.obterLista().forEach(System.out::println);
         itemCardapioService.obterDisponiveis().forEach(System.out::println);

       // dados.keySet();
       // dados.values();

       // dados.values().forEach(System.out::println);
    }

}
