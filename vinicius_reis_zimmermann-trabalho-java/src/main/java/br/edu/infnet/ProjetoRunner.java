package br.edu.infnet;

import br.edu.infnet.exception.IdentificadorDuplicadoException;
import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.*;
import br.edu.infnet.repository.ItemCardapioRepository;
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
public class ProjetoRunner implements CommandLineRunner {
    private final ItemCardapioService itemCardapioService;
    private final ClienteService clienteService;
    private final LanchoneteService lanchoneteService;
    private final PedidoService pedidoService;
    private final ItemCardapioRepository itemCardapioRepository;

    public ProjetoRunner(ItemCardapioService itemCardapioService,
                         ClienteService clienteService,
                         LanchoneteService lanchoneteService,
                         PedidoService pedidoService, ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioService = itemCardapioService;
        this.clienteService = clienteService;
        this.lanchoneteService = lanchoneteService;
        this.pedidoService = pedidoService;
        this.itemCardapioRepository = itemCardapioRepository;
    }

    private void demonstrarRepository() {

        exibirTitulo("Demonstração do Repository (JPA)");

        List<ItemCardapio> itensParaIncluir = List.of(
                new Lanche(null, "xBurguer", new BigDecimal("35.00"), true,
                        "Carne artesanal, molho, picles, alface, pão com gergelim, batata palha", true),
                new Bebida(null, "Suco de Laranja", new BigDecimal("9.90"), true, 500, false)
        );

        List<ItemCardapio> itensIncluidos = itemCardapioRepository.saveAll(itensParaIncluir);
        exibirItens("1 - saveAll - itens incluídos", itensIncluidos);

        exibirItens("2 - count / findAll - itens no banco", itemCardapioRepository.findAll());
        System.out.printf("total: %d%n", itemCardapioRepository.count());

        exibirTitulo("3 - findById - busca por id");
        for (ItemCardapio item : itensIncluidos) {
            itemCardapioRepository.findById(item.getId()).ifPresentOrElse(
                    this::exibirItem,
                    () -> System.out.printf("id=[%d] não encontrado%n", item.getId())
            );
        }

        ItemCardapio itemRemovido = itensIncluidos.get(0);
        Long idRemovido = itemRemovido.getId();

        exibirTitulo("4 - deleteById - remoção do item id=[" + idRemovido + "]");
        System.out.printf("existe antes do delete?  %s%n", simNao(itemCardapioRepository.existsById(idRemovido)));

        itemCardapioRepository.deleteById(idRemovido);

        System.out.printf("existe depois do delete? %s%n", simNao(itemCardapioRepository.existsById(idRemovido)));
        System.out.printf("total após o delete: %d%n", itemCardapioRepository.count());

        exibirItens("5 - findAll - itens restantes", itemCardapioRepository.findAll());
    }

    private void exibirTitulo(String titulo) {
        System.out.println();
        System.out.println(" " + titulo + " ");
    }

    private void exibirItens(String titulo, List<ItemCardapio> itens) {
        exibirTitulo(titulo);
        if (itens.isEmpty()) {
            System.out.println("nenhum item");
            return;
        }
        itens.forEach(this::exibirItem);
    }

    private void exibirItem(ItemCardapio item) {
        System.out.printf("   id=[%d] %s%n", item.getId(), item);
    }

    private String simNao(boolean valor) {
        return valor ? "sim" : "não";
    }

    @Override
    public void run(String... args) throws Exception {

        demonstrarRepository();

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
