package com.controle.de.concorrencia.crebito.Crebito;

import com.controle.de.concorrencia.crebito.Crebito.model.Cliente;
import com.controle.de.concorrencia.crebito.Crebito.repository.ClienteRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CrebitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrebitoApplication.class, args);
	}
	void criarCliente(){
		Cliente cliente1 = new Cliente();

		cliente1.setId(1L);
		cliente1.setLimite(10000);
		cliente1.setSaldoInicial(0);

		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setLimite(10000);
		cliente2.setSaldoInicial(0);
	}

}
