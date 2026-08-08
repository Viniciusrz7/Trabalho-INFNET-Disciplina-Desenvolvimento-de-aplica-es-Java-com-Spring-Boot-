package br.edu.infnet;

import br.edu.infnet.exception.IdentificadorDuplicadoException;
import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.*;
import br.edu.infnet.service.ItemService;
import br.edu.infnet.model.domain.util.CNPJ;
import br.edu.infnet.model.domain.util.CPF;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Loader implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        CNPJ cnpjLanchonete = new CNPJ("06.990.590/0001-23");
        Lanchonete lanchonete = new Lanchonete(1L, "Super Lanches", true, cnpjLanchonete    );

        Lanche lanche = new Lanche(1L, "X-Tudo Artesanal", new BigDecimal("35.50"), true, "Pão brioche, blend 200g, queijo, bacon, ovo", true);
        Bebida bebida = new Bebida(2L, "Coca-Cola", new BigDecimal("7.00"), true, 350, false);

        lanchonete.adicionarItemcardapio(lanche);
        lanchonete.adicionarItemcardapio(bebida);
        CPF cpf = new CPF("142.013.666-69");
        Cliente cliente = new Cliente(1L, "Carlos Silva", cpf);

        lanchonete.cadastrarCliente(cliente);

        Pedido pedido = new Pedido(1L, 1001, LocalDateTime.now(), true, cliente);

        pedido.adicionarItem(lanche);
        pedido.adicionarItem(bebida);

        lanchonete.adicionarPedido(pedido);

        List<ItemCardapio> dados = List.of(lanche);
        dados.forEach(System.out::println);
        System.out.println(" Dados da Lanchonete ");
        System.out.println(lanchonete);
        ItemService itemService = new ItemService();
        itemService.incluir(lanche);

        for (ItemCardapio item : lanchonete.getCardapio()) {
            System.out.println("- " + item.descreverPreparo());
        }

        for (Pedido pedido1 : lanchonete.getHistoricoPedidos()) {
            System.out.println(pedido1);
        }
        try {
            itemService.incluir(lanche);
        } catch (IdentificadorDuplicadoException e) {
            System.out.println("[ERROR]" + e.getMessage() + "Nome:" + lanche.getNome());
        } catch (RecursoNaoEncontradoException | IllegalArgumentException e) {
            System.out.println("[ERROR]" + e.getMessage());
        }
         itemService.obterLista().forEach(System.out::println);
         itemService.obterDisponiveis().forEach(System.out::println);
       // dados.keySet();
       // dados.values();

       // dados.values().forEach(System.out::println);
    }

}
