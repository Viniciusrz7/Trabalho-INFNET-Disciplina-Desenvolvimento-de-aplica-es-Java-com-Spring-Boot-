package br.edu.infnet.controller;

import br.edu.infnet.dto.EnderecoDTO;
import br.edu.infnet.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Operation(summary = "Consulta um endereço por CEP", description = "Consulta a API externa ViaCEP através do OpenFeign e retorna o endereço correspondente")
    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> getByCep(@Parameter(description = "CEP com 8 dígitos, com ou sem traço") @PathVariable String cep) {
        EnderecoDTO endereco = enderecoService.buscarPorCep(cep);
        return ResponseEntity.ok(endereco);
    }
}
