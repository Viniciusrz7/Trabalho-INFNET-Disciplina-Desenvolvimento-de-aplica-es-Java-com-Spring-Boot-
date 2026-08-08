package br.edu.infnet.model.domain.service;

import br.edu.infnet.model.domain.ItemCardapio;
import br.edu.infnet.model.domain.service.BaseGenerics.BaseService;
import br.edu.infnet.model.domain.util.Identificavel;

import java.util.ArrayList;
import java.util.List;

public class ItemService extends BaseService<ItemCardapio> {

    public List<ItemCardapio> obterDisponiveis(){
        List<ItemCardapio> disponiveis = new ArrayList<>();
        obterLista();

        for(ItemCardapio itemcarpio : obterLista()){
            if(itemcarpio.isDisponivel()) {
                disponiveis.add(itemcarpio);
            }
        }
        return disponiveis;
    }

    public List<ItemCardapio> obterListaDisponiveis(){
        return obterLista().stream().filter(ItemCardapio::isDisponivel).toList();
    }
}
