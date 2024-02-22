package com.controle.de.concorrencia.crebito.Crebito.repository;

import com.controle.de.concorrencia.crebito.Crebito.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
}