package com.controle.de.concorrencia.crebito.Crebito.service;

import com.controle.de.concorrencia.crebito.Crebito.dto.ClienteDto;
import com.controle.de.concorrencia.crebito.Crebito.infra.exceptions.ClienteNaoEncontradoException;
import com.controle.de.concorrencia.crebito.Crebito.model.Cliente;
import com.controle.de.concorrencia.crebito.Crebito.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID fornecido: " + id));
    }

    @Override
    public void cadastrarCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente(clienteDto);
        clienteRepository.save(cliente);
    }
}
