package com.controle.de.concorrencia.crebito.Crebito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CrebitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrebitoApplication.class, args);
	}

}
