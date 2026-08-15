package br.edu.infnet.service;

import br.edu.infnet.model.domain.Cliente;
import br.edu.infnet.model.domain.ItemCardapio;
import br.edu.infnet.service.BaseGenerics.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService extends BaseService<Cliente> {
    public List<Cliente> buscarPorNomeDeclarativo(String termo){
        return obterLista().stream().filter(cliente -> cliente.getNome().toLowerCase().contains(termo.toLowerCase())).toList();
    }
}
