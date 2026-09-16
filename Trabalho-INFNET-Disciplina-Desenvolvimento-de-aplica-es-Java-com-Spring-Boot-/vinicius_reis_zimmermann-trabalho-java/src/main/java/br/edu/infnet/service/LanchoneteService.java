package br.edu.infnet.service;


import br.edu.infnet.dto.LanchoneteResponse;
import br.edu.infnet.entrega.client.EntregaClient;
import br.edu.infnet.entrega.client.EntregaResponse;
import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.Lanchonete;
import br.edu.infnet.repository.LanchoneteRepository;
import br.edu.infnet.service.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanchoneteService {

    private final LanchoneteRepository lanchoneteRepository;
    private final EntregaClient entregaClient;
//    private final EntregaService entregaService;

    public LanchoneteService(LanchoneteRepository lanchoneteRepository, EntregaClient entregaClient) {
        this.lanchoneteRepository = lanchoneteRepository;
        this.entregaClient = entregaClient;
    }

    public LanchoneteResponse obterDetalhes(Long id) {
        Lanchonete lanchonete = getById(id);
        return converterParaResponse(lanchonete);
    }

    private LanchoneteResponse converterParaResponse(Lanchonete lanchonete) {
        return new LanchoneteResponse(lanchonete.getId(), lanchonete.getNome(), lanchonete.isAtiva(), lanchonete.getEntregaIds());
    }

    public LanchoneteResponse matricularEntrega(Long lanchoneteId, Long entregaId) {
        Lanchonete lanchonete = getById(lanchoneteId);
        EntregaResponse entrega = entregaClient.obterPorId(entregaId);
        lanchonete.getEntregaIds().contains(entrega.id());

        if (lanchonete.getEntregaIds().contains(entrega.id())) {
            throw new IllegalArgumentException("A entrega ja esta matriculada");
        }

        lanchonete.adicionarEntrega(entrega.id());

        Lanchonete lanchoneteAtualizada = lanchoneteRepository.save(lanchonete);
        return converterParaResponse(lanchoneteAtualizada);
    }

    public void incluir(Lanchonete lanchonete) {
        lanchoneteRepository.save(lanchonete);
    }

    public void alterar(Long id, Lanchonete lanchonete) {
        Lanchonete existente = getById(id);
        existente.setNome(lanchonete.getNome());
        existente.setCnpj(lanchonete.getCnpj());
        existente.setAtiva(lanchonete.isAtiva());
        lanchoneteRepository.save(existente);
    }

    public void excluir(Long id) {
        Lanchonete lanchonete = getById(id);
        lanchoneteRepository.delete(lanchonete);
    }

    public Lanchonete getById(Long id) {
        return lanchoneteRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Nenhum objeto encontrado para o identificador" + id + "."));
    }

    public List<Lanchonete> obterLista() {
        return lanchoneteRepository.findAll();
    }

    public List<Lanchonete> buscarPorNome(String termo) {
        Validation.validarTermo(termo);
        return lanchoneteRepository.findByNomeContainingIgnoreCase(termo);
    }

    public Lanchonete alterarParcialmente(Long id, Lanchonete lanchonete) {
        Lanchonete existente = getById(id);
        aplicarAlteracoesParciais(existente, lanchonete);
        return lanchoneteRepository.save(existente);
    }

    private void aplicarAlteracoesParciais(Lanchonete existente, Lanchonete novosDados) {
        if (novosDados.getNome() != null) {
            existente.setNome(novosDados.getNome());
        }
        if (novosDados.getCnpj() != null) {
            existente.setCnpj(novosDados.getCnpj());
        }
        if (novosDados.isAtiva() != null) {
            existente.setAtiva(novosDados.isAtiva());
        }
    }
}
