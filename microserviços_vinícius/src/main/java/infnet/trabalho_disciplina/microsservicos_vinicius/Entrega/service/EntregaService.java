package infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.service;

import infnet.trabalho_disciplina.microsservicos_vinicius.exception.EntregaNaoEncontradoException;
import infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.repository.EntregaRepository;
import infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.domain.Entrega;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository){
        this.entregaRepository=entregaRepository;
    }

    public Entrega incluir(Entrega entrega){
        return entregaRepository.save(entrega);
    }

    public List<Entrega> obterLista(){
        return entregaRepository.findAll();
    }

   /* public List<Entrega> obterPorLanchonete(Long lanchoneteId) {
        return entregaRepository.findByLanchoneteId(lanchoneteId);
    }*/

    public Entrega obterPorId(Long id){
        return entregaRepository.findById(id).orElseThrow(()-> new EntregaNaoEncontradoException(id));
    }

    public Entrega alterar(Long id, Entrega entrega){
        Entrega existente = obterPorId(id);

        existente.setNomeCliente(entrega.getNomeCliente());
        existente.setData(entrega.getData());
        existente.setAtiva(entrega.isAtiva());
        existente.setEndereco(entrega.getEndereco());
        existente.setHora(entrega.getHora());
        existente.setFrete(entrega.getFrete());
        existente.setValorEntrega(entrega.getValorEntrega());


        return entregaRepository.save(existente);
    }

    public void excluir(Long id){
        Entrega existente = obterPorId(id);

        entregaRepository.delete(existente);
    }

    public List<Entrega> obterAtivos(){
        return entregaRepository.findByAtivaTrue();
    }

    public List<Entrega> obterPorNome(String nome){
        return entregaRepository.findByNomeClienteContainingIgnoreCase(nome);
    }

    public Optional<Entrega> obterPorEndereco(String endereco){
        return entregaRepository.findByEndereco(endereco);
    }

}
