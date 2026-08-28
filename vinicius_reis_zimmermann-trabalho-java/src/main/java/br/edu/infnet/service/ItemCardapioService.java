package br.edu.infnet.service;

import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.ItemCardapio;
import br.edu.infnet.repository.ItemCardapioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCardapioService {

    private final ItemCardapioRepository itemcardapioRepository;

    public ItemCardapioService(ItemCardapioRepository itemcardapioRepository) {
        this.itemcardapioRepository = itemcardapioRepository;
    }

    public void incluir(ItemCardapio itemCardapio){
         itemcardapioRepository.save(itemCardapio);
    }

    public void alterar(Long id, ItemCardapio itemCardapio){
        ItemCardapio existente = getById(id);
        existente.setNome(itemCardapio.getNome());
        existente.setLanchonete(itemCardapio.getLanchonete());
        existente.setDisponivel(itemCardapio.getDisponivel());
        existente.setPreco(itemCardapio.getPreco());
        itemcardapioRepository.save(existente);
    }

    public void excluir(Long id){

        ItemCardapio itemCardapio = getById(id);

        itemcardapioRepository.delete(itemCardapio);

     /*   itemcardapioRepository.deleteById(id);*/

    }

    public ItemCardapio getById(Long id){
     /*   Optional<ItemCardapio> itemcardapio = itemcardapioRepository.findById(id);
        if(itemcardapio.isPresent()){
            return itemcardapio.get();
        }
        throw new RecursoNaoEncontradoException("Nenhum objeto encontrado para o identificador" + id + ".");*/
        return itemcardapioRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Nenhum objeto encontrado para o identificador" + id + "."));
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
