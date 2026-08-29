package br.edu.infnet.client;

import br.edu.infnet.dto.EnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCepClient", url = "${app.viacep.url}", configuration = ViaCepClientConfiguration.class)
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    EnderecoDTO buscarPorCep(@PathVariable("cep") String cep);
}
