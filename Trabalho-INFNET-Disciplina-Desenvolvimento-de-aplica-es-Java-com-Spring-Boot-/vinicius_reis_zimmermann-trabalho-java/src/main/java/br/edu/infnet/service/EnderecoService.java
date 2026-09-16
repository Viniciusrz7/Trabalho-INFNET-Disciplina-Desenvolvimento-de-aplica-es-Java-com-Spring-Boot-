package br.edu.infnet.service;

import br.edu.infnet.dto.EnderecoDTO;
import br.edu.infnet.service.client.ViaCepClient;
import br.edu.infnet.exception.RecursoNaoEncontradoException;
import br.edu.infnet.service.validation.Validation;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class EnderecoService {

    private final ViaCepClient viaCepClient;

    public EnderecoService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public EnderecoDTO buscarPorCep(String cep) {
        String cepNumerico = Validation.validarCep(cep);

        return Stream.ofNullable(viaCepClient.buscarPorCep(cepNumerico))
                .filter(this::foiEncontrado)
                .findFirst()
                .orElseThrow(() -> new RecursoNaoEncontradoException("Nenhum endereço encontrado para o CEP " + cep + "."));

    }
    private boolean foiEncontrado(EnderecoDTO endereco) {
        return endereco.cep() != null;
    }
}
