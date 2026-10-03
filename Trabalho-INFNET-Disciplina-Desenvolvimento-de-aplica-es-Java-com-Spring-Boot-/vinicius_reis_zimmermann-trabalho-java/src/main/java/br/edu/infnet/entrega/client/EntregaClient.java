package br.edu.infnet.entrega.client;

import br.edu.infnet.batch.EntregaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "entrega-service", url = "${entrega.service.url}")
public interface EntregaClient {

    @GetMapping("/entregas/{id}")
    EntregaResponse obterPorId(@PathVariable Long id);

    @PostMapping("/entregas")
    void incluir(@RequestBody EntregaRequest entrega);
}
