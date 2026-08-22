package br.edu.infnet.service;

import br.edu.infnet.model.domain.ItemCardapio;
import br.edu.infnet.repository.ItemCardapioRepository;
import br.edu.infnet.service.BaseGenerics.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCardapioService extends BaseService<ItemCardapio> {

    private final ItemCardapioRepository itemcardapioRepository;

    public ItemCardapioService(ItemCardapioRepository itemcardapioRepository) {
        this.itemcardapioRepository = itemcardapioRepository;
    }

    public List<ItemCardapio> obterLista(){
        return itemcardapioRepository.findAll();
    }

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

    public List<ItemCardapio> buscarPorNome(String termo){
            List<ItemCardapio> resultado = new ArrayList<>();
            for(ItemCardapio itemcardapio : obterLista()){
                if(itemcardapio.getNome().toLowerCase().contains(termo.toLowerCase())){
                    resultado.add(itemcardapio);
                }
            }
            return resultado;
    }

    public List<ItemCardapio> buscarPorNomeDeclarativo(String termo){
        return obterLista().stream().filter(itemcardapio -> itemcardapio.getNome().toLowerCase().contains(termo.toLowerCase())).toList();
    }
}
