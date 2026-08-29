package br.edu.infnet.service;

import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.Cliente;
import br.edu.infnet.service.validation.Validation;
import br.edu.infnet.repository.ClienteRepository;
import br.edu.infnet.service.BaseGenerics.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService extends BaseService<Cliente> {
   /* public List<Cliente> buscarPorNomeDeclarativo(String termo){
        return obterLista().stream().filter(cliente -> cliente.getNome().toLowerCase().contains(termo.toLowerCase())).toList();
    }*/

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void incluir (Cliente cliente){
        clienteRepository.save(cliente);
    }

    public void alterar(Long id, Cliente cliente){
        Cliente existente = getById(id);
        existente.setNome(cliente.getNome());
        existente.setCpf(cliente.getCpf());
        existente.setLanchonete(cliente.getLanchonete());
        clienteRepository.save(existente);
    }

    public List<Cliente> obterLista(){
        return clienteRepository.findAll();
    }

    public List<Cliente> buscarPorNome(String termo){
        Validation.validarTermo(termo);
        return clienteRepository.findByNomeContainingIgnoreCase(termo);
    }

    public Cliente getById(Long id){
     /*   Optional<ItemCardapio> itemcardapio = itemcardapioRepository.findById(id);
        if(itemcardapio.isPresent()){
            return itemcardapio.get();
        }
        throw new RecursoNaoEncontradoException("Nenhum objeto encontrado para o identificador" + id + ".");*/
        return clienteRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Nenhum objeto encontrado para o identificador" + id + "."));
    }

    @Override
    public void excluir(Long id) {
        Cliente cliente = getById(id);
        clienteRepository.delete(cliente);
    }

    public Cliente alterarParcialmente(Long id, Cliente cliente) {
        Cliente existente = getById(id);

        aplicarAlteracoesParciais(existente, cliente);

        return clienteRepository.save(existente);
    }

    private void aplicarAlteracoesParciais(
            Cliente existente,
            Cliente novosDados) {

        if (novosDados.getNome() != null) {
            existente.setNome(novosDados.getNome());
        }

        if (novosDados.getCpf() != null) {
            existente.setCpf(novosDados.getCpf());
        }

        if (novosDados.getLanchonete() != null) {
            existente.setLanchonete(novosDados.getLanchonete());
        }
    }
}
