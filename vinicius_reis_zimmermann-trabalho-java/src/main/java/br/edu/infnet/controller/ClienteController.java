package br.edu.infnet.controller;

import br.edu.infnet.model.domain.Cliente;
import br.edu.infnet.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService=clienteService;
    }
    @GetMapping
    public List<Cliente> obterLista(){
        return clienteService.obterLista();
    }
}
