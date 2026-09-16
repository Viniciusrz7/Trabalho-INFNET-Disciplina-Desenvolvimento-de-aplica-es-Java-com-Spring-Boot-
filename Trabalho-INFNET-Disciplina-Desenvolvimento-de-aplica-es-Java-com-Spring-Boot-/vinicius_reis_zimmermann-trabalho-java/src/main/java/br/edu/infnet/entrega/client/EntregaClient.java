package br.edu.infnet.entrega.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "entrega-service",url = "${entrega.service.url}")
public interface EntregaClient {
    @GetMapping("/entregas/{id}")
    EntregaResponse obterPorId(@PathVariable Long id);
}
