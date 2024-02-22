package com.controle.de.concorrencia.crebito.Crebito.controller;


import com.controle.de.concorrencia.crebito.Crebito.dto.TransacoesDto;
import com.controle.de.concorrencia.crebito.Crebito.model.Cliente;
import com.controle.de.concorrencia.crebito.Crebito.service.TransacoesServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/cliente")
public class TransacoesController {

    private final TransacoesServiceImpl transacoesService;

    public TransacoesController(TransacoesServiceImpl transacoesService){
        this.transacoesService = transacoesService;
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<Map<String,Double>> realizarTransacao(@PathVariable("id") Long id, @RequestBody TransacoesDto transacoesDto){
        Map<String,Double> saldoLimite = transacoesService.realizarTransacao(id,transacoesDto);
        return new ResponseEntity<>(saldoLimite, HttpStatus.OK);
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<Map<String, Object>> gerarExtrato(@PathVariable("id") Long id){
        Map<String, Object> extrato = transacoesService.gerarExtrato(id);
        return new ResponseEntity<>(extrato, HttpStatus.OK);
    }
}
