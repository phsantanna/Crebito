package com.controle.de.concorrencia.crebito.Crebito.service;

import com.controle.de.concorrencia.crebito.Crebito.model.Cliente;
import com.controle.de.concorrencia.crebito.Crebito.repository.ClienteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ClienteInitialization {


    private ClienteRepository clienteRepository;
    public ClienteInitialization(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setLimite(10000);
        cliente1.setSaldoInicial(0);

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setLimite(10000);
        cliente2.setSaldoInicial(0);

        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);
    }
}

