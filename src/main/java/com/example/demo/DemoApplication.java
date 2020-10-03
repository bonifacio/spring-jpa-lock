package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.UUID;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PersonComponent personComponent, PersonStatusComponent personStatusComponent) {
		return args -> {
			log.info("iniciando o experimento...");
			log.info("criando registro para João...");

			var joao = new Person(UUID.randomUUID().toString(), "João do JPA", "joao@jpa.com", "INICIADO");

			personComponent.create(joao);

			log.info("registro finalizado...");

			log.info("Mudando o status para PENDENTE");
			personStatusComponent.changeStatus(joao.getId(), "PENDENTE");

			log.info("Mudando o status para CANCELADO");
			personStatusComponent.changeStatus(joao.getId(), "CANCELADO");

			personComponent.changeName(joao.getId(), "João do Spring");

			Thread.sleep(10000);

			var joaoAtualizado = personComponent.find(joao.getId());
			log.info(joaoAtualizado.toString());

		};
	}
}
