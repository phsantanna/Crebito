package com.controle.de.concorrencia.crebito.Crebito.service;


import com.controle.de.concorrencia.crebito.Crebito.dto.TransacoesDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TransacoesService {
    public Map<String,Double> realizarTransacao(Long id, TransacoesDto transacoesDto);

    public Map<String, Object> gerarExtrato(Long id);
}
