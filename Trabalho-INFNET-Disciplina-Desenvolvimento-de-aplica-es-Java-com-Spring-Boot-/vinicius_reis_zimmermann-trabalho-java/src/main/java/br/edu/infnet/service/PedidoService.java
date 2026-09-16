package br.edu.infnet.service;

import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.Pedido;
import br.edu.infnet.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void incluir(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public void alterar(Long id, Pedido pedido) {
        Pedido existente = getById(id);
        existente.setNumeroPedido(pedido.getNumeroPedido());
        existente.setDataHoraEmissao(pedido.getDataHoraEmissao());
        existente.setAtivo(pedido.isAtivo());
        existente.setCliente(pedido.getCliente());
        existente.setItensSelecionados(pedido.getItensSelecionados());
        existente.setLanchonete(pedido.getLanchonete());
        pedidoRepository.save(existente);
    }

    public void excluir(Long id) {
        Pedido pedido = getById(id);
        pedidoRepository.delete(pedido);
    }

    public Pedido getById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Nenhum objeto encontrado para o identificador" + id + "."));
    }

    public List<Pedido> obterLista() {
        return pedidoRepository.findAll();
    }

    public Pedido alterarParcialmente(Long id, Pedido pedido) {
        Pedido existente = getById(id);
        aplicarAlteracoesParciais(existente, pedido);
        return pedidoRepository.save(existente);
    }

    private void aplicarAlteracoesParciais(Pedido existente, Pedido novosDados) {
        if (novosDados.getNumeroPedido() != null) {
            existente.setNumeroPedido(novosDados.getNumeroPedido());
        }
        if (novosDados.getDataHoraEmissao() != null) {
            existente.setDataHoraEmissao(novosDados.getDataHoraEmissao());
        }
        if (novosDados.isAtivo() != null) {
            existente.setAtivo(novosDados.isAtivo());
        }
        if (novosDados.getCliente() != null) {
            existente.setCliente(novosDados.getCliente());
        }
        if (novosDados.getItensSelecionados() != null) {
            existente.setItensSelecionados(novosDados.getItensSelecionados());
        }
        if (novosDados.getLanchonete() != null) {
            existente.setLanchonete(novosDados.getLanchonete());
        }
    }
}
