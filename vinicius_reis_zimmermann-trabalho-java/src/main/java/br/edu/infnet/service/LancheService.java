package br.edu.infnet.service;

import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.Lanche;
import br.edu.infnet.repository.LancheRepository;
import br.edu.infnet.service.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancheService {

    private final LancheRepository lancheRepository;

    public LancheService(LancheRepository lancheRepository) {
        this.lancheRepository = lancheRepository;
    }

    public void incluir(Lanche lanche) {
        lancheRepository.save(lanche);
    }

    public void alterar(Long id, Lanche lanche) {
        Lanche existente = getById(id);
        existente.setNome(lanche.getNome());
        existente.setLanchonete(lanche.getLanchonete());
        existente.setDisponivel(lanche.getDisponivel());
        existente.setPreco(lanche.getPreco());
        existente.setIngredientes(lanche.getIngredientes());
        existente.setArtesanal(lanche.iseArtesanal());
        lancheRepository.save(existente);
    }

    public void excluir(Long id) {
        Lanche lanche = getById(id);
        lancheRepository.delete(lanche);
    }

    public Lanche getById(Long id) {
        return lancheRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Nenhum objeto encontrado para o identificador" + id + "."));
    }

    public List<Lanche> obterLista() {
        return lancheRepository.findAll();
    }

    public List<Lanche> buscarPorNome(String termo) {
        Validation.validarTermo(termo);
        return lancheRepository.findByNomeContainingIgnoreCase(termo);
    }

    public List<Lanche> obterDisponiveis() {
        return lancheRepository.findByDisponivelTrue();
    }

    public Lanche alterarParcialmente(Long id, Lanche lanche) {
        Lanche existente = getById(id);
        aplicarAlteracoesParciais(existente, lanche);
        return lancheRepository.save(existente);
    }

    private void aplicarAlteracoesParciais(Lanche existente, Lanche novosDados) {
        if (novosDados.getNome() != null) {
            existente.setNome(novosDados.getNome());
        }
        if (novosDados.getPreco() != null) {
            existente.setPreco(novosDados.getPreco());
        }
        if (novosDados.getDisponivel() != null) {
            existente.setDisponivel(novosDados.getDisponivel());
        }
        if (novosDados.getLanchonete() != null) {
            existente.setLanchonete(novosDados.getLanchonete());
        }
        if (novosDados.getIngredientes() != null) {
            existente.setIngredientes(novosDados.getIngredientes());
        }
        if (novosDados.iseArtesanal() != null) {
            existente.setArtesanal(novosDados.iseArtesanal());
        }
    }
}
