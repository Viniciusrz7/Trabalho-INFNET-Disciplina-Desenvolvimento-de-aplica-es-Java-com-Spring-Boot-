package br.edu.infnet.service;

import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.Lanchonete;
import br.edu.infnet.repository.LanchoneteRepository;
import br.edu.infnet.service.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanchoneteService {

    private final LanchoneteRepository lanchoneteRepository;

    public LanchoneteService(LanchoneteRepository lanchoneteRepository) {
        this.lanchoneteRepository = lanchoneteRepository;
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
