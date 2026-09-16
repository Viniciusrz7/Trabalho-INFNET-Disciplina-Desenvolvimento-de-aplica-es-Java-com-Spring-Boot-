package br.edu.infnet.service;

import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.model.domain.Bebida;
import br.edu.infnet.repository.BebidaRepository;
import br.edu.infnet.service.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BebidaService {

    private final BebidaRepository bebidaRepository;

    public BebidaService(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    public void incluir(Bebida bebida) {
        bebidaRepository.save(bebida);
    }

    public void alterar(Long id, Bebida bebida) {
        Bebida existente = getById(id);
        existente.setNome(bebida.getNome());
        existente.setLanchonete(bebida.getLanchonete());
        existente.setDisponivel(bebida.getDisponivel());
        existente.setPreco(bebida.getPreco());
        existente.setVolumeMl(bebida.getVolumeMl());
        existente.seteAlcoolica(bebida.iseAlcoolica());
        bebidaRepository.save(existente);
    }

    public void excluir(Long id) {
        Bebida bebida = getById(id);
        bebidaRepository.delete(bebida);
    }

    public Bebida getById(Long id) {
        return bebidaRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Nenhum objeto encontrado para o identificador" + id + "."));
    }

    public List<Bebida> obterLista() {
        return bebidaRepository.findAll();
    }

    public List<Bebida> buscarPorNome(String termo) {
        Validation.validarTermo(termo);
        return bebidaRepository.findByNomeContainingIgnoreCase(termo);
    }

    public List<Bebida> obterDisponiveis() {
        return bebidaRepository.findByDisponivelTrue();
    }

    public Bebida alterarParcialmente(Long id, Bebida bebida) {
        Bebida existente = getById(id);
        aplicarAlteracoesParciais(existente, bebida);
        return bebidaRepository.save(existente);
    }

    private void aplicarAlteracoesParciais(Bebida existente, Bebida novosDados) {
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
        if (novosDados.getVolumeMl() != null) {
            existente.setVolumeMl(novosDados.getVolumeMl());
        }
        if (novosDados.iseAlcoolica() != null) {
            existente.seteAlcoolica(novosDados.iseAlcoolica());
        }
    }
}
