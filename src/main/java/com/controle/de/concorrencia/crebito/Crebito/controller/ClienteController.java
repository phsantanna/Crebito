package com.controle.de.concorrencia.crebito.Crebito.controller;

import com.controle.de.concorrencia.crebito.Crebito.dto.ClienteDto;
import com.controle.de.concorrencia.crebito.Crebito.service.ClienteServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteServiceImpl clienteService;
    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cadastrar")
    public void cadastrarCliente(@RequestBody ClienteDto clienteDto) {
        clienteService.cadastrarCliente(clienteDto);
    }
}
