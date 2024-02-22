package com.controle.de.concorrencia.crebito.Crebito.service;

import com.controle.de.concorrencia.crebito.Crebito.dto.TransacoesDto;
import com.controle.de.concorrencia.crebito.Crebito.enums.Tipo;
import com.controle.de.concorrencia.crebito.Crebito.infra.exceptions.SaldoMenorQueLimiteException;
import com.controle.de.concorrencia.crebito.Crebito.model.Cliente;
import com.controle.de.concorrencia.crebito.Crebito.model.Transacoes;
import com.controle.de.concorrencia.crebito.Crebito.repository.TransacoesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransacoesServiceImpl implements TransacoesService {

    private final TransacoesRepository transacoesRepository;
    private final ClienteServiceImpl clienteService;

    public TransacoesServiceImpl(TransacoesRepository transacoesRepository, ClienteServiceImpl clienteService) {
        this.transacoesRepository = transacoesRepository;
        this.clienteService = clienteService;
    }

    @Override
    public Map<String, Double> realizarTransacao(Long id, TransacoesDto transacoesDto) {
        Cliente cliente = clienteService.buscarClientePorId(id);

        double novoSaldo = cliente.getSaldoInicial() - transacoesDto.valor();
        double verificarSaldo = Math.abs(novoSaldo);

        if (transacoesDto.tipo() == Tipo.DEBITO && verificarSaldo > cliente.getLimite()){
            throw new SaldoMenorQueLimiteException("O saldo do cliente é menor que o limite.");
        }

        cliente.setSaldoInicial(novoSaldo);
        Transacoes transacoes = new Transacoes(transacoesDto.valor(), transacoesDto.tipo(), transacoesDto.descricao());
        transacoes.setCliente(cliente);
        String dataFormatada = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")) + "Z";
        transacoes.setRealizada_em(LocalDateTime.parse(dataFormatada, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")));
        transacoesRepository.save(transacoes);

        Map<String, Double> saldoLimite = new HashMap<>();
        saldoLimite.put("saldo", cliente.getSaldoInicial());
        saldoLimite.put("limite", cliente.getLimite());

        return saldoLimite;
    }

    @Override
    public Map<String, Object> gerarExtrato(Long id) {
        Map<String, Object> saldo = new LinkedHashMap<>();
        Map<String, Object> extrato = new LinkedHashMap<>();

        String dataFormatada = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")) + "Z";
        Cliente cliente = clienteService.buscarClientePorId(id);

        extrato.put("total", cliente.getSaldoInicial());
        extrato.put("data_extrato", dataFormatada);
        extrato.put("limite", cliente.getLimite());

        List<Transacoes> ultimasTransacoes = cliente.getTransacoes();
        List<Map<String, Object>> ultimas10Transacoes = new ArrayList<>();

        // Adicionando detalhes das últimas 10 transações
        int startIndex = Math.max(ultimasTransacoes.size() - 10, 0);
        for (int i = startIndex; i < ultimasTransacoes.size(); i++) {
            Transacoes transacao = ultimasTransacoes.get(i);
            Map<String, Object> detalheTransacao = new LinkedHashMap<>();
            detalheTransacao.put("valor", transacao.getValor());
            detalheTransacao.put("tipo", transacao.getTipo());
            detalheTransacao.put("descricao", transacao.getDescricao());
            detalheTransacao.put("realizada_em", transacao.getRealizada_em());
            ultimas10Transacoes.add(detalheTransacao);
        }

        saldo.put("saldo", extrato);
        saldo.put("ultimas_transacoes", ultimas10Transacoes);

        return saldo;
    }
}