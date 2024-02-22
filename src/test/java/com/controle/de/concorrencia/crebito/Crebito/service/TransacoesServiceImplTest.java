package com.controle.de.concorrencia.crebito.Crebito.service;

import com.controle.de.concorrencia.crebito.Crebito.dto.TransacoesDto;
import com.controle.de.concorrencia.crebito.Crebito.enums.Tipo;
import com.controle.de.concorrencia.crebito.Crebito.infra.exceptions.SaldoMenorQueLimiteException;
import com.controle.de.concorrencia.crebito.Crebito.model.Cliente;
import com.controle.de.concorrencia.crebito.Crebito.model.Transacoes;
import com.controle.de.concorrencia.crebito.Crebito.repository.TransacoesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransacoesServiceImplTest {

    @Mock
    private ClienteServiceImpl clienteService;

    @Mock
    private TransacoesRepository transacoesRepository;

    @InjectMocks
    private TransacoesServiceImpl transacoesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRealizarTransacaoSuccess() {

        Long id = 1L;
        TransacoesDto transacoesDto = new TransacoesDto(100, "DESCRICAO", Tipo.DEBITO);

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setSaldoInicial(-200);
        cliente.setLimite(10000);

        when(clienteService.buscarClientePorId(anyLong())).thenReturn(cliente);

        Map<String, Double> resultado = transacoesService.realizarTransacao(cliente.getId(), transacoesDto);

        assertEquals(-300, resultado.get("saldo"));
        assertEquals(10000, resultado.get("limite"));

        verify(transacoesRepository, times(1)).save(any());
    }

    @Test
    void testRealizarTransacaoFail() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setSaldoInicial(-10000);
        cliente.setLimite(10000);
        TransacoesDto transacoesDto = new TransacoesDto(100, "Descrição", Tipo.DEBITO);

        when(clienteService.buscarClientePorId(anyLong())).thenReturn(cliente);

        assertThrows(SaldoMenorQueLimiteException.class, () -> {
            transacoesService.realizarTransacao(cliente.getId(), transacoesDto);
        });

        verify(transacoesRepository, never()).save(any());
    }

    @Test
    void gerarExtrato() {
        Cliente cliente = new Cliente();
        cliente.setSaldoInicial(100);
        cliente.setLimite(500);

        List<Transacoes> transacoes = new ArrayList<>();
        transacoes.add(criarTransacao(50, "DEBITO", "Compra 1", LocalDateTime.now().minusDays(2)));
        transacoes.add(criarTransacao(33, "CREDITO", "Depósito 1", LocalDateTime.now().minusDays(1)));
        transacoes.add(criarTransacao(2, "DEBITO", "Compra 2", LocalDateTime.now()));

        for (int i = 0; i < 10; i++) {
            transacoes.add(criarTransacao(10, "DEBITO", "Compra " + (i + 3), LocalDateTime.now()));
        }

        cliente.setTransacoes(transacoes);

        when(clienteService.buscarClientePorId(anyLong())).thenReturn(cliente);

        Map<String, Object> resultado = transacoesService.gerarExtrato(1L);
        System.out.println("Resultado do método gerarExtrato: " + resultado);

        assertNotNull(resultado);

        assertTrue(resultado.containsKey("saldo"));
        Map<String, Object> saldoMap = (Map<String, Object>) resultado.get("saldo");
        assertEquals(100, (double) saldoMap.get("total"));

        String dataExtrato = (String) saldoMap.get("data_extrato");
        assertTrue(dataExtrato.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{6}Z"));

        assertTrue(resultado.containsKey("ultimas_transacoes"));
        List<Map<String, Object>> ultimasTransacoes = (List<Map<String, Object>>) resultado.get("ultimas_transacoes");

        assertTrue(ultimasTransacoes.size() <= 10);

        LocalDateTime dataMaisRecente = LocalDateTime.now().minusDays(1);
        for (Map<String, Object> detalheTransacao : ultimasTransacoes) {
            LocalDateTime dataTransacao = (LocalDateTime) detalheTransacao.get("realizada_em");
            assertTrue(dataTransacao.isAfter(dataMaisRecente) || dataTransacao.isEqual(dataMaisRecente));
        }
        System.out.println(ultimasTransacoes);
    }



    private Transacoes criarTransacao(double valor, String tipo, String descricao, LocalDateTime realizadaEm) {
        Transacoes transacao = new Transacoes();
        transacao.setValor(valor);
        transacao.setTipo(Tipo.DEBITO);
        transacao.setDescricao(descricao);
        transacao.setRealizada_em(realizadaEm);
        return transacao;
    }
}