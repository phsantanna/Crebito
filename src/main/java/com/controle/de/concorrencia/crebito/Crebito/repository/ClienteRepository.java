package com.controle.de.concorrencia.crebito.Crebito.repository;

import com.controle.de.concorrencia.crebito.Crebito.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}