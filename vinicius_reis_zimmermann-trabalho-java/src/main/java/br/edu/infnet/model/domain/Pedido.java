package br.edu.infnet.model.domain;

import br.edu.infnet.model.domain.util.Identificavel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido implements Identificavel {
    private Long id;
    private Integer numeroPedido;
    private LocalDateTime dataHoraEmissao;
    private Boolean ativo;
    private Cliente cliente;
    private List<ItemCardapio> itensSelecionados = new ArrayList<ItemCardapio>();
    private Lanchonete lanchonete;

    public Pedido(Long id, Integer numeroPedido, LocalDateTime dataHoraEmissao,Boolean ativo, Cliente cliente) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.dataHoraEmissao = dataHoraEmissao;
        this.ativo=ativo;
        this.cliente = cliente;
    }
    public Pedido(){

    }
    @Override
    public String toString() {
        String nomeCliente = (cliente != null) ? cliente.getNome() : "Sem cliente";
        return String.format("Pedido {numero='%d', emissão='%td/%<tm/%<tY %<tH:%<tM',ativo='%s', cliente='%s', itens=%s}",
                numeroPedido,
                dataHoraEmissao,
                ativo ? "sim": "nao",
                nomeCliente,
                itensSelecionados);
    }

    public void adicionarItem(ItemCardapio item){
        if(item == null){
            throw new IllegalArgumentException("O item não pode ser nulo!!!");
        }
        itensSelecionados.add(item);

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setLanchonete(Lanchonete lanchonete){
        this.lanchonete=lanchonete;
    }
    public Lanchonete getLanchonete() {
        return lanchonete;
    }
    public Integer getNumeroPedido() {
        return numeroPedido;
    }
    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
    public LocalDateTime getDataHoraEmissao() {
        return dataHoraEmissao;
    }
    public void setDataHoraEmissao(LocalDateTime dataHoraEmissao) {
        this.dataHoraEmissao = dataHoraEmissao;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<ItemCardapio> getItensSelecionados() {
        return Collections.unmodifiableList(itensSelecionados);
    }
    public void setItensSelecionados(List<ItemCardapio> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }
    public Boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
