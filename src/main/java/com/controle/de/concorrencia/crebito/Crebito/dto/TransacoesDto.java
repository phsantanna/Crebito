package com.controle.de.concorrencia.crebito.Crebito.dto;

import com.controle.de.concorrencia.crebito.Crebito.enums.Tipo;

public record TransacoesDto(double valor, String descricao, Tipo tipo) {
}
