package infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.service;

import infnet.trabalho_disciplina.microsservicos_vinicius.Entrega.dto.EntregaResponse;
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

    public EntregaResponse incluir(Entrega entrega){
        return converterParaResponse(entregaRepository.save(entrega));
    }

    public List<EntregaResponse> obterLista(){
        return converterParaResponse(entregaRepository.findAll());
    }

   /* public List<EntregaResponse> obterPorLanchonete(Long lanchoneteId) {
        return converterParaResponse(entregaRepository.findByLanchoneteId(lanchoneteId));
    }*/

    public EntregaResponse obterPorId(Long id){
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Entrega entrega = obterEntidadePorId(id);
        return converterParaResponse(entrega);
    }

    private Entrega obterEntidadePorId(Long id){
        return entregaRepository.findById(id).orElseThrow(()-> new EntregaNaoEncontradoException(id));
    }

    private EntregaResponse converterParaResponse(Entrega entrega) {
        return new EntregaResponse(
                entrega.getId(),
                entrega.getNomeCliente(),
                entrega.getEndereco(),
                entrega.getFrete(),
                entrega.getData(),
                entrega.getHora(),
                entrega.getValorEntrega(),
                entrega.isAtiva());
    }

    private List<EntregaResponse> converterParaResponse(List<Entrega> entregas) {
        return entregas.stream().map(this::converterParaResponse).toList();
    }

    public EntregaResponse alterar(Long id, Entrega entrega){
        Entrega existente = obterEntidadePorId(id);

        existente.setNomeCliente(entrega.getNomeCliente());
        existente.setData(entrega.getData());
        existente.setAtiva(entrega.isAtiva());
        existente.setEndereco(entrega.getEndereco());
        existente.setHora(entrega.getHora());
        existente.setFrete(entrega.getFrete());
        existente.setValorEntrega(entrega.getValorEntrega());


        return converterParaResponse(entregaRepository.save(existente));
    }

    public void excluir(Long id){
        Entrega existente = obterEntidadePorId(id);

        entregaRepository.delete(existente);
    }

    public List<EntregaResponse> obterAtivos(){
        return converterParaResponse(entregaRepository.findByAtivaTrue());
    }

    public List<EntregaResponse> obterPorNome(String nome){
        return converterParaResponse(entregaRepository.findByNomeClienteContainingIgnoreCase(nome));
    }

    public Optional<EntregaResponse> obterPorEndereco(String endereco){
        return entregaRepository.findByEndereco(endereco).map(this::converterParaResponse);
    }

}
