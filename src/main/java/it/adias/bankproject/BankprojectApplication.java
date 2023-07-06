package it.adias.bankproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // pour dire a spring que chaue fois qu'il rencontre created date de faire il fera appel a la AbstracEntity
public class BankprojectApplication {

	public static void main(String[] args) {

		SpringApplication.run(BankprojectApplication.class, args);
	}

}
